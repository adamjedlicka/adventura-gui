package jeda00.adventura.places;

import jeda00.adventura.core.events.FlavourTextEvent;
import jeda00.adventura.core.events.GameOverEvent;
import jeda00.adventura.core.events.ItemPutDownEvent;
import jeda00.adventura.core.events.RockRoomDestroyedEvent;
import jeda00.container.Container;
import jeda00.eventbus.EventBus;

/**
 * Rock room which needs to be detroyed to win the game
 */
public class RockRoom extends AbstractPlace {

    private EventBus eventBus = Container.resolve(EventBus.class);

    public RockRoom() {
        itemManager.setCapacity(1);

        eventBus.subscribe(ItemPutDownEvent.class, this::onItemPutDown);
    }

    /**
     * When item is put down to this room check if it wins or looses the game
     */
    private void onItemPutDown(ItemPutDownEvent event) {
        if (!event.getPlace().equals(this)) {
            return;
        }

        if (event.getItem().getName().equals("rock")) {
            eventBus.emit(new FlavourTextEvent("It doesn't seem to have any effect."));
        }

        if (event.getItem().getName().equals("scissors")) {
            eventBus.emit(new GameOverEvent("You sumbled over and stabbed yourselve with the scissors."));
        }

        if (event.getItem().getName().equals("paper")) {
            eventBus.emit(new FlavourTextEvent("You wrapped the room in paper. It's supper effective."));
            itemManager.getAndRemoveItem("paper");
            itemManager.setCapacity(0);

            eventBus.emit(new RockRoomDestroyedEvent());
        }
    }

    @Override
    public String getName() {
        return "rock_room";
    }

    @Override
    public String getEntryMessage() {
        return "Whole room is built from rocks. Looks like it could be damaged by paper.";
    }

}
