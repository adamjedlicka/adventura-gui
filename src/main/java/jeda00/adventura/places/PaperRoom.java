package jeda00.adventura.places;

import jeda00.adventura.core.events.FlavourTextEvent;
import jeda00.adventura.core.events.GameOverEvent;
import jeda00.adventura.core.events.ItemPutDownEvent;
import jeda00.adventura.core.events.PaperRoomDestroyedEvent;
import jeda00.container.Container;
import jeda00.eventbus.EventBus;

/**
 * Paper room which needs to be destroyed to win the game
 */
public class PaperRoom extends AbstractPlace {

    private final EventBus eventBus;

    public PaperRoom() {
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

        if (event.getItem().getName().equals("paper")) {
            eventBus.emit(new FlavourTextEvent("The room is now a bit more papery."));
        }

        if (event.getItem().getName().equals("rock")) {
            eventBus.emit(new GameOverEvent("You are now wrapped in paper and slowly suffocating..."));
        }

        if (event.getItem().getName().equals("scissors")) {
            eventBus.emit(new FlavourTextEvent("You successfully snipped the room in half."));
            itemManager.getAndRemoveItem("scissors");
            itemManager.setCapacity(0);

            eventBus.emit(new PaperRoomDestroyedEvent());
        }
    }

    @Override
    public String getName() {
        return "paper_room";
    }

    @Override
    public String getEntryMessage() {
        return "Room made of paper. Shame you don't have water.";
    }

}
