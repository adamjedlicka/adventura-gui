package jeda00.adventura.core;

import java.util.ArrayList;
import java.util.List;

import jeda00.adventura.core.events.PlaceChangedEvent;
import jeda00.adventura.support.Place;
import jeda00.adventura.support.PlaceManager;
import jeda00.container.Container;
import jeda00.eventbus.EventBus;

/**
 * Default implementation of PlaceManager interface
 */
public class DefaultPlaceManager implements PlaceManager {

    private final EventBus eventBus = Container.resolve(EventBus.class);

    private List<Place> places;

    private Place currentPlace;

    public DefaultPlaceManager() {
        places = new ArrayList<>();
    }

    @Override
    public void addPlace(Place place) {
        places.add(place);
    }

    @Override
    public Place getCurrentPlace() {
        return currentPlace;
    }

    @Override
    public void setCurrentPlace(Place currentPlace) {
        Place oldPlace = this.currentPlace;

        this.currentPlace = currentPlace;

        eventBus.emit(new PlaceChangedEvent(oldPlace, currentPlace));
    }

    @Override
    public List<Place> getPlaces() {
        return places;
    }

}
