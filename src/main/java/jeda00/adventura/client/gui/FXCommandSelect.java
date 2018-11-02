package jeda00.adventura.client.gui;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import jeda00.adventura.commands.util.Argument;
import jeda00.adventura.core.events.CommandExecutedEvent;
import jeda00.adventura.support.Command;
import jeda00.adventura.support.CommandManager;
import jeda00.adventura.support.Game;
import jeda00.adventura.support.Response;
import jeda00.container.Container;
import jeda00.eventbus.EventBus;

/**
 * Input method using ComboBoxes instead of plain text field
 */
public class FXCommandSelect extends HBox {

    private final EventBus eventBus = Container.resolve(EventBus.class);

    private final Game game = Container.resolve(Game.class);

    private final CommandManager commandManager = Container.resolve(CommandManager.class);

    private final ComboBox<String> commandSelect;

    private final Button confirm;

    private final HBox arguments;

    public FXCommandSelect() {
        commandSelect = new ComboBox<>();
        confirm = new Button();
        arguments = new HBox();

        commandSelect.setOnAction(this::onCommandAction);

        confirm.setText("Confirm");
        confirm.setOnAction(this::onConfirmAction);

        Region region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);

        for (Command command : commandManager.getCommands()) {
            commandSelect.getItems().add(command.getName());
        }

        getChildren().add(commandSelect);
        getChildren().add(arguments);
        getChildren().add(region);
        getChildren().add(confirm);

        eventBus.subscribe(CommandExecutedEvent.class, this::onCommandExecuted);
    }

    /**
     * Handle when command was selected in the main combo box
     */
    private void onCommandAction(ActionEvent event) {
        arguments.getChildren().clear();

        Optional<Command> optional = commandManager.getCommand(commandSelect.getValue());
        if (!optional.isPresent()) {
            return;
        }

        Command command = optional.get();

        for (Argument argument : command.getArguments().getArguments()) {
            ComboBox<String> comboBox = new ComboBox<String>();
            for (String value : argument.getPossibleValues()) {
                comboBox.getItems().add(value);
            }
            arguments.getChildren().add(comboBox);
        }
    }

    /**
     * Handle when confirm/execute button was pressed
     */
    @SuppressWarnings("unchecked")
    private void onConfirmAction(ActionEvent event) {
        StringBuilder sb = new StringBuilder();

        sb.append(commandSelect.getValue());

        for (Node argument : arguments.getChildren()) {
            if (!(argument instanceof ComboBox)) {
                continue;
            }

            sb.append(" ");

            sb.append(((ComboBox<String>) argument).getValue());
        }

        String command = sb.toString();

        Response response = game.execute(command);

        eventBus.emit(new CommandExecutedEvent(command, response));
    }

    /**
     * After command was succesfully executed by the game reset the combo boxes to
     * the default state
     */
    private void onCommandExecuted(CommandExecutedEvent event) {
        commandSelect.getSelectionModel().select("");

        arguments.getChildren().clear();
    }

}
