package jeda00.adventura.core;

import jeda00.adventura.core.events.PaperRoomDestroyedEvent;
import jeda00.adventura.core.events.RockRoomDestroyedEvent;
import jeda00.adventura.core.events.ScissorsRoomDestroyedEvent;
import jeda00.adventura.support.WinCondition;
import jeda00.container.Container;
import jeda00.eventbus.EventBus;

/**
 * Default win condition achieved by destroying all the rooms
 */
public class DefaultWinCondition implements WinCondition {

    private final EventBus eventBus;

    private boolean isRockRoomDestroyed;

    private boolean isPaperRoomDestroyed;

    private boolean isScissorsRoomDestroyed;

    public DefaultWinCondition() {
        eventBus = Container.resolve(EventBus.class);

        isRockRoomDestroyed = false;
        isPaperRoomDestroyed = false;
        isScissorsRoomDestroyed = false;

        eventBus.subscribe(RockRoomDestroyedEvent.class, e -> isRockRoomDestroyed = true);
        eventBus.subscribe(PaperRoomDestroyedEvent.class, e -> isPaperRoomDestroyed = true);
        eventBus.subscribe(ScissorsRoomDestroyedEvent.class, e -> isScissorsRoomDestroyed = true);
    }

    @Override
    public boolean isFulfilled() {
        return isRockRoomDestroyed && isPaperRoomDestroyed && isScissorsRoomDestroyed;
    }

    @Override
    public String getMessage() {
        return "You successfully destroyed all the rooms. Congratulations!";
    }

}
