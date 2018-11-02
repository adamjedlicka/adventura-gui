package jeda00.eventbus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class DefaultEventBus implements EventBus {

    private Map<Class<?>, List<Consumer<Object>>> handlers;

    private Map<Class<?>, List<Object>> sentEvents;

    public DefaultEventBus() {
        handlers = new HashMap<>();
        sentEvents = new HashMap<>();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> void subscribe(Class<T> event, Consumer<T> handler) {
        handlers.computeIfAbsent(event, k -> new ArrayList<>());

        handlers.get(event).add((Consumer<Object>) handler);
    }

    @Override
    public <T> void unsubscribe(Class<T> event, Consumer<T> handler) {
        handlers.put(event, handlers.get(event).stream().filter(h -> h.equals(handler)).collect(Collectors.toList()));
    }

    @Override
    public <T> void emit(T event) {
        sentEvents.computeIfAbsent(event.getClass(), k -> new ArrayList<>());

        sentEvents.get(event.getClass()).add(event);

        Collection<Consumer<Object>> subscribers = handlers.get(event.getClass());
        if (subscribers == null) {
            return;
        }

        subscribers.stream().forEach(handler -> handler.accept(event));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> getSentEvents(Class<T> event) {
        return (List<T>) sentEvents.get(event);
    }
}
