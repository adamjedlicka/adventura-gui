package jeda00.adventura.core.events;

import jeda00.adventura.support.Item;
import jeda00.adventura.support.Place;

/**
 * Item was put down from the inventory to the ground
 */
public class ItemPutDownEvent {

    private Item item;

    private Place place;

    public ItemPutDownEvent(Item item, Place place) {
        this.item = item;
        this.place = place;
    }

    /**
     * Returns the item that was put down
     */
    public Item getItem() {
        return item;
    }

    /**
     * Returns the place to which the item was put down
     */
    public Place getPlace() {
        return place;
    }

}
