package jeda00.eventbus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class EventBusTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testEventBusCanBeSubscribedToAndEventFired() {
        EventBus eventBus = new DefaultEventBus();

        eventBus.subscribe(String.class, event -> assertTrue(event instanceof String));

        eventBus.subscribe(String.class, event -> System.out.print(event.getClass()));

        eventBus.subscribe(String.class, event -> assertEquals("Hello, World!", event));
        eventBus.subscribe(String.class, event -> assertNotEquals("Bye, World!", event));

        eventBus.emit("Hello, World!");
    }

    public void simpleSubscriber(String event) {
        assertEquals("Should not be called", event);
    }

    @Test
    public void testSubscriberCanUnsibscribe() {
        EventBus eventBus = new DefaultEventBus();

        eventBus.subscribe(String.class, this::simpleSubscriber);

        eventBus.subscribe(String.class, event -> event.equals("Proper value"));

        eventBus.unsubscribe(String.class, this::simpleSubscriber);

        eventBus.emit("Proper value");
    }

    @Test
    public void emitingEventNooneListensToDoesNotCauseException() {
        EventBus eventBus = new DefaultEventBus();

        eventBus.emit("event");

        assertTrue(true);
    }

}
