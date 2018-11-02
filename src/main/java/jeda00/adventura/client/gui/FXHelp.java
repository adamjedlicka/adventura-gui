package jeda00.adventura.client.gui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import jeda00.adventura.client.gui.events.ShowHelpEvent;
import jeda00.container.Container;
import jeda00.eventbus.EventBus;

/**
 * Help window
 */
public class FXHelp extends Stage {

    private final EventBus eventBus = Container.resolve(EventBus.class);

    private WebView webView;

    public FXHelp() {
        StringBuilder sb = new StringBuilder();

        try {
            Files.lines(Paths.get("src/main/resources/help.html")).forEach(line -> sb.append(line).append('\n'));
        } catch (IOException e) {
            sb.append("Could not load src/main/resources/help.html");
        }

        webView = new WebView();
        webView.getEngine().loadContent(sb.toString());

        setTitle("Help");
        setScene(new Scene(webView, 720, 540));

        eventBus.subscribe(ShowHelpEvent.class, e -> show());
    }

}
