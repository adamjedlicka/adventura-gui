package jeda00.adventura.core.events;

import jeda00.adventura.support.Item;
import jeda00.adventura.support.Place;

/**
 * Item was picked up from the ground to the inventory
 */
public class ItemPickedUpEvent {

    private Item item;

    private Place place;

    public ItemPickedUpEvent(Item item, Place place) {
        this.item = item;
        this.place = place;
    }

    /**
     * Returns the item that was picked up
     */
    public Item getItem() {
        return item;
    }

    /**
     * Returns the place from which the item was picked up
     */
    public Place getPlace() {
        return place;
    }

}
