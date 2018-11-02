package jeda00.adventura.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jeda00.adventura.commands.util.Argument;
import jeda00.adventura.core.events.GameOverEvent;
import jeda00.adventura.core.events.GameWonEvent;
import jeda00.adventura.core.responses.BadArgumentValueResponse;
import jeda00.adventura.core.responses.BadArgumentsResponse;
import jeda00.adventura.core.responses.DefaultResponse;
import jeda00.adventura.core.util.CommandParser;
import jeda00.adventura.support.Command;
import jeda00.adventura.support.CommandManager;
import jeda00.adventura.support.Game;
import jeda00.adventura.support.ItemManager;
import jeda00.adventura.support.Response;
import jeda00.adventura.support.WinCondition;
import jeda00.container.Container;
import jeda00.eventbus.EventBus;

/**
 * Default implementation of the Game interface
 */
public class DefaultGame implements Game {

    private final EventBus eventBus;

    private final CommandManager commandManager;

    private final ItemManager inventory;

    private List<WinCondition> winConditions;

    private boolean isRunning;

    private String welcomeMessage;

    private String exitMessage;

    public DefaultGame() {
        eventBus = Container.resolve(EventBus.class);
        commandManager = Container.resolve(CommandManager.class);
        inventory = Container.resolve(ItemManager.class).setCapacity(2);

        winConditions = new ArrayList<>();
        winConditions.add(new DefaultWinCondition());

        isRunning = true;
        welcomeMessage = "Welcome to Rock, Paper, Scissors!";
        exitMessage = "";

        eventBus.subscribe(GameOverEvent.class, event -> {
            exitMessage = event.getReason();
            isRunning = false;
        });

        eventBus.subscribe(GameWonEvent.class, event -> {
            exitMessage = event.getReason();
            isRunning = false;
        });
    }

    @Override
    public Response execute(String input) {
        if (!isRunning) {
            return null;
        }

        CommandParser parser = new CommandParser(input);

        Optional<Command> optional = commandManager.getCommand(parser.getCommand());
        if (!optional.isPresent()) {
            return new DefaultResponse("Unknown command.");
        }

        Response response = checkArgumentsAndExecute(optional.get(), parser.getArguments());

        checkWinConditions();

        return response;
    }

    /**
     * Check if arguments are OK and if yes execute the command and return its
     * response. If not return response indicating what went wrong with the
     * arguments
     */
    private Response checkArgumentsAndExecute(Command command, List<String> arguments) {
        if (arguments.size() < command.getArguments().getMin() || arguments.size() > command.getArguments().getMax()) {
            return new BadArgumentsResponse(command);
        }

        for (int i = 0; i < arguments.size(); i++) {
            Argument argument = command.getArguments().getArguments().get(i);
            List<String> possibleValues = argument.getPossibleValues();
            if (possibleValues.size() == 0) {
                continue;
            }

            if (possibleValues.contains(arguments.get(i))) {
                continue;
            }

            return new BadArgumentValueResponse(argument);
        }

        return command.execute(arguments);
    }

    /**
     * Check win conditions and if any passes emit GameWonEvent
     */
    private void checkWinConditions() {
        for (WinCondition winCondition : winConditions) {
            if (winCondition.isFulfilled()) {
                eventBus.emit(new GameWonEvent(winCondition.getMessage()));
            }
        }
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    @Override
    public String getExitMessage() {
        return exitMessage;
    }

    @Override
    public ItemManager getInventory() {
        return inventory;
    }

}
