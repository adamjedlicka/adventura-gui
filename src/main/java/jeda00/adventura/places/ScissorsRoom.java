package jeda00.adventura.places;

import jeda00.adventura.core.events.FlavourTextEvent;
import jeda00.adventura.core.events.GameOverEvent;
import jeda00.adventura.core.events.ItemPutDownEvent;
import jeda00.adventura.core.events.ScissorsRoomDestroyedEvent;
import jeda00.container.Container;
import jeda00.eventbus.EventBus;

/**
 * Scissors room which needs to be destroyed to win the game
 */
public class ScissorsRoom extends AbstractPlace {

    private final EventBus eventBus;

    public ScissorsRoom() {
        eventBus = Container.resolve(EventBus.class);

        eventBus.subscribe(ItemPutDownEvent.class, this::onItemPutDown);
    }

    /**
     * When item is put down to this room check if it wins or looses the game
     */
    private void onItemPutDown(ItemPutDownEvent event) {
        if (!event.getPlace().equals(this)) {
            return;
        }

        if (event.getItem().getName().equals("scissors")) {
            eventBus.emit(new FlavourTextEvent("Scissors! Scissors everywhere!"));
        }

        if (event.getItem().getName().equals("paper")) {
            eventBus.emit(new GameOverEvent("Jesus fucking christ..."));
        }

        if (event.getItem().getName().equals("rock")) {
            eventBus.emit(new FlavourTextEvent("The room is now blunt."));
            itemManager.getAndRemoveItem("rock");
            itemManager.setCapacity(0);

            eventBus.emit(new ScissorsRoomDestroyedEvent());
        }
    }

    @Override
    public String getName() {
        return "scissors_room";
    }

    @Override
    public String getEntryMessage() {
        return "You have never seen a room made of scissors.";
    }

}
