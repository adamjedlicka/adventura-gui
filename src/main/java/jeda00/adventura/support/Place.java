package jeda00.adventura.support;

import java.util.List;

/**
 * Places create map and player can move through them or put items into their
 * item managhers
 */
public interface Place {

    /**
     * Returns name of the place
     */
    public String getName();

    /**
     * Return entry message which gets printed out when player enters the room
     */
    public String getEntryMessage();

    /**
     * Returns the list of all neighbours neighbouring this place
     */
    public List<Place> getNeighbours();

    /**
     * Returns item manager managing items in this pace
     */
    public ItemManager getItemManager();

}
