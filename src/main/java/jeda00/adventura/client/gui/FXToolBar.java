package jeda00.adventura.client.gui;

import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import jeda00.adventura.client.gui.events.ChangeInputMethodEvent;
import jeda00.adventura.client.gui.events.ShowHelpEvent;
import jeda00.adventura.client.gui.events.ShowMapEvent;
import jeda00.adventura.core.events.NewGameEvent;
import jeda00.container.Container;
import jeda00.eventbus.EventBus;

/**
 * Toolbar
 */
public class FXToolBar extends ToolBar {

    private final EventBus eventBus = Container.resolve(EventBus.class);

    public FXToolBar() {
        Button newGame = new Button("New game");
        newGame.setOnAction(e -> eventBus.emit(new NewGameEvent()));
        getItems().add(newGame);

        Button showMap = new Button("Show map");
        showMap.setOnAction(e -> eventBus.emit(new ShowMapEvent()));
        getItems().add(showMap);

        Button showHelp = new Button("Show help");
        showHelp.setOnAction(e -> eventBus.emit(new ShowHelpEvent()));
        getItems().add(showHelp);

        Button changeInput = new Button("Change input method");
        changeInput.setOnAction(e -> eventBus.emit(new ChangeInputMethodEvent()));
        getItems().add(changeInput);
    }

}
