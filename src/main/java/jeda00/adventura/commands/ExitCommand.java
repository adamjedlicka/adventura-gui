package jeda00.adventura.commands;

import java.util.List;

import jeda00.adventura.core.events.GameOverEvent;
import jeda00.adventura.support.Response;
import jeda00.container.Container;
import jeda00.eventbus.EventBus;

/**
 * Exits the game
 */
public class ExitCommand extends AbstractCommand {

    private EventBus eventBus = Container.resolve(EventBus.class);

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public Response execute(List<String> arguments) {
        eventBus.emit(new GameOverEvent("User used the exit command."));

        return response("Exiting game...");
    }

    @Override
    public String getHelp() {
        return "Exits the game.";
    }

}
