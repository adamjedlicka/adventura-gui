package jeda00.adventura.client.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import jeda00.adventura.core.events.PlaceChangedEvent;
import jeda00.adventura.support.Place;
import jeda00.adventura.support.PlaceManager;
import jeda00.container.Container;
import jeda00.eventbus.EventBus;

/**
 * Display of all neighbouring places
 */
public class FXNeighbours extends VBox {

    private final EventBus eventBus = Container.resolve(EventBus.class);

    private final PlaceManager placeManager = Container.resolve(PlaceManager.class);

    private final Label label;

    private final VBox places;

    public FXNeighbours() {
        setSpacing(10);
        setPadding(new Insets(10));

        label = new Label();
        places = new VBox();

        label.setText("Neighbours:");
        label.setUnderline(true);

        getChildren().addAll(label, places);

        eventBus.subscribe(PlaceChangedEvent.class, e -> refreshNeighbours());

        refreshNeighbours();
    }

    /**
     * When place has changed refresh the display to show new neighbours of the
     * current place
     */
    private void refreshNeighbours() {
        places.getChildren().clear();

        for (Place neighbour : placeManager.getCurrentPlace().getNeighbours()) {
            Label label = new Label();
            label.setText(neighbour.getName());

            places.getChildren().add(label);
        }
    }

}
