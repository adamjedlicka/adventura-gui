package jeda00.adventura.client.gui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import jeda00.adventura.client.gui.events.ShowHelpEvent;
import jeda00.adventura.client.gui.events.ShowMapEvent;
import jeda00.adventura.core.events.NewGameEvent;
import jeda00.container.Container;
import jeda00.eventbus.EventBus;

/**
 * Menu bar
 */
public class FXMenuBar extends MenuBar {

    private final EventBus eventBus = Container.resolve(EventBus.class);

    public FXMenuBar() {
        Menu menu = new Menu("Game");

        MenuItem newGame = new MenuItem("New game");
        newGame.setOnAction(e -> eventBus.emit(new NewGameEvent()));
        menu.getItems().add(newGame);

        MenuItem showMap = new MenuItem("Show map");
        showMap.setOnAction(e -> eventBus.emit(new ShowMapEvent()));
        menu.getItems().add(showMap);

        MenuItem showHelp = new MenuItem("Show help");
        showHelp.setOnAction(e -> eventBus.emit(new ShowHelpEvent()));
        menu.getItems().add(showHelp);

        getMenus().add(menu);
    }

}
