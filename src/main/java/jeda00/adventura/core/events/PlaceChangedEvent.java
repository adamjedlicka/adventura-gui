package jeda00.adventura.core.events;

import jeda00.adventura.support.Place;

/**
 * Player went to another place
 */
public class PlaceChangedEvent {

    private Place from;

    private Place to;

    public PlaceChangedEvent(Place from, Place to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the original place from which the player went
     */
    public Place getFrom() {
        return from;
    }

    /**
     * Returns the new place to which player went
     */
    public Place getTo() {
        return to;
    }

}
