package jeda00.adventura.client.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import jeda00.adventura.client.gui.events.ChangeInputMethodEvent;
import jeda00.adventura.core.events.GameOverEvent;
import jeda00.adventura.core.events.GameWonEvent;
import jeda00.adventura.core.events.NewGameEvent;
import jeda00.container.Container;
import jeda00.eventbus.EventBus;

/**
 * Main window of the application
 */
public class FXRoot extends BorderPane {

    private final EventBus eventBus = Container.resolve(EventBus.class);

    private final FXInput input;

    private final FXOutput output;

    private final FXMenuBar menuBar;

    private final FXToolBar toolBar;

    private final FXNeighbours neighbours;

    private final FXInventory inventory;

    private final FXCommandSelect commandSelect;

    private final FXMap map;

    private final FXHelp help;

    private boolean inputSelect;

    public FXRoot() {
        input = new FXInput();
        output = new FXOutput();
        menuBar = new FXMenuBar();
        toolBar = new FXToolBar();
        neighbours = new FXNeighbours();
        inventory = new FXInventory();
        commandSelect = new FXCommandSelect();
        map = new FXMap();
        help = new FXHelp();

        inputSelect = true;

        VBox vBoxTop = new VBox(menuBar, toolBar);
        VBox vBoxRight = new VBox(neighbours, inventory);
        vBoxRight.setMinWidth(150);

        setCenter(output);
        setBottom(commandSelect);
        setTop(vBoxTop);
        setRight(vBoxRight);

        eventBus.subscribe(GameOverEvent.class, this::onGameOver);
        eventBus.subscribe(GameWonEvent.class, this::onGameWon);
        eventBus.subscribe(NewGameEvent.class, e -> {
            map.hide();
            help.hide();
        });

        eventBus.subscribe(ChangeInputMethodEvent.class, e -> {
            if (inputSelect) {
                setBottom(input);
            } else {
                setBottom(commandSelect);
            }

            inputSelect = !inputSelect;
        });
    }

    /**
     * When game is over display messagebox informing the user
     */
    private void onGameOver(GameOverEvent event) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Game over!");
        alert.setContentText("Click OK and start a new game.");
        alert.setHeaderText(event.getReason());
        alert.show();
    }

    /**
     * When game is won display messagebox informing the user
     */
    private void onGameWon(GameWonEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("You win!");
        alert.setContentText("Click OK and start a new game.");
        alert.setHeaderText(event.getReason());
        alert.show();
    }

}
