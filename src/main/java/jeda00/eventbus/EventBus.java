package jeda00.eventbus;

import java.util.List;
import java.util.function.Consumer;

public interface EventBus {

    /**
     * Subscribe to receiving events of specified type
     *
     * @param event   Event class which handler subscribes to
     * @param handler Handler for receiving events
     */
    public <T> void subscribe(Class<T> event, Consumer<T> handler);

    /**
     * Unsubscribe from receiving events of specified type
     *
     * @param event   Event class which handler un-subscribes from
     * @param handler Handler for receiving events
     */
    public <T> void unsubscribe(Class<T> event, Consumer<T> handler);

    /**
     * Emit new events and notify all subscribers
     *
     * @param event Event instance to emit
     */
    public <T> void emit(T event);

    /**
     * Returns all sent events for the given event class
     *
     * @param event Class of the events to be returned
     */
    public <T> List<T> getSentEvents(Class<T> event);

}
