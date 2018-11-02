package jeda00.adventura.client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jeda00.adventura.client.gui.FXRoot;
import jeda00.adventura.client.txt.TextClient;
import jeda00.adventura.core.Init;
import jeda00.adventura.core.events.NewGameEvent;
import jeda00.container.Container;
import jeda00.eventbus.EventBus;

/**
 * Main class
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("4IT115 - jeda00");
        setSceneFor(stage);
        stage.show();

        stage.setOnHiding(e -> System.exit(0));
    }

    private void setSceneFor(Stage stage) {
        FXRoot root = new FXRoot();
        Scene scene = new Scene(root, 720, 540);

        stage.setScene(scene);

        Container.resolve(EventBus.class).subscribe(NewGameEvent.class, e -> setSceneFor(stage));
    }

    public static void main(String[] args) {
        Init.init();

        if (args.length > 0 && args[0].equals("--txt")) {
            TextClient textClient = new TextClient();
            textClient.run();
        } else {
            launch(args);
        }

        System.exit(0);
    }

}
