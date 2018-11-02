package jeda00.adventura.client.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import jeda00.adventura.core.events.ItemPickedUpEvent;
import jeda00.adventura.core.events.ItemPutDownEvent;
import jeda00.container.Container;
import jeda00.eventbus.EventBus;

/**
 * Inventory
 */
public class FXInventory extends VBox {

    private final EventBus eventBus = Container.resolve(EventBus.class);

    public FXInventory() {
        setSpacing(10);

        Label label = new Label();
        label.setPadding(new Insets(10));
        label.setText("Inventory:");
        label.setUnderline(true);
        getChildren().add(label);

        eventBus.subscribe(ItemPickedUpEvent.class, this::onItemPickedUp);
        eventBus.subscribe(ItemPutDownEvent.class, this::onItemPutDown);
    }

    /**
     * When item was picked up add its image to the inventory window
     */
    private void onItemPickedUp(ItemPickedUpEvent event) {
        Image image = new Image(event.getItem().getName() + ".png");
        ImageView imageView = new ImageView(image);

        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setUserData(event.getItem().getName());

        getChildren().add(imageView);
    }

    /**
     * When item was put down remove its image from the inventory window
     */
    public void onItemPutDown(ItemPutDownEvent event) {
        getChildren()
                .removeIf(node -> node instanceof ImageView && node.getUserData().equals(event.getItem().getName()));
    }

}
