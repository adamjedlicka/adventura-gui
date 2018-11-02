package jeda00.adventura.client.gui;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import jeda00.adventura.client.gui.events.ShowMapEvent;
import jeda00.adventura.client.gui.map.MapGenerator;
import jeda00.adventura.core.events.PlaceChangedEvent;
import jeda00.container.Container;
import jeda00.eventbus.EventBus;

/**
 * Map window
 */
public class FXMap extends Stage {

    private final EventBus eventBus = Container.resolve(EventBus.class);

    private Pane pane;

    private ImageView imageView;

    private MapGenerator generator;

    public FXMap() {
        pane = new Pane();
        imageView = new ImageView();
        generator = new MapGenerator();

        pane.getChildren().add(imageView);

        setTitle("Map");
        setScene(new Scene(pane, 720, 540));

        eventBus.subscribe(PlaceChangedEvent.class, event -> refreshMap());
        eventBus.subscribe(ShowMapEvent.class, event -> show());

        refreshMap();
    }

    /**
     * Refresh map when place has changed
     */
    private void refreshMap() {
        imageView.setImage(new Image(generator.generate().toInputStream()));
    }

}
