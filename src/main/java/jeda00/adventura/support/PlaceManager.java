package jeda00.adventura.support;

import java.util.List;

/**
 * Manages places. Can be used as world map
 */
public interface PlaceManager {

    /**
     * Add place to the list of all places
     */
    public void addPlace(Place place);

    /**
     * Returns the current place
     */
    public Place getCurrentPlace();

    /**
     * Sets new current place
     */
    public void setCurrentPlace(Place currentPlace);

    /**
     * Returns the list of places
     */
    public List<Place> getPlaces();

}
