package jeda00.adventura.client.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import jeda00.adventura.core.events.CommandExecutedEvent;
import jeda00.adventura.core.events.GameOverEvent;
import jeda00.adventura.core.events.GameWonEvent;
import jeda00.adventura.support.Game;
import jeda00.adventura.support.Response;
import jeda00.container.Container;
import jeda00.eventbus.EventBus;

/**
 * Default input method as plain text fied
 */
public class FXInput extends TextField {

    private EventBus eventBus = Container.resolve(EventBus.class);

    private Game game = Container.resolve(Game.class);

    public FXInput() {
        setOnAction(this::onAction);

        Platform.runLater(() -> requestFocus());

        eventBus.subscribe(GameOverEvent.class, this::onGameOver);
        eventBus.subscribe(GameWonEvent.class, this::onGameWon);
    }

    /**
     * Handle when enter key was pressed and execute the commadn
     */
    private void onAction(ActionEvent event) {
        String command = getText();
        Response response = game.execute(command);

        setText("");

        eventBus.emit(new CommandExecutedEvent(command, response));
    }

    /**
     * When game overis over disable the input
     */
    private void onGameOver(GameOverEvent event) {
        setEditable(false);
    }

    /**
     * When game is won disable the input
     */
    private void onGameWon(GameWonEvent event) {
        setEditable(false);
    }

}
