package jeda00.adventura.support;

import java.util.List;
import java.util.Optional;

/**
 * Manages items. Can be added to the rooms or can be used as ineventory or any
 * other containert
 */
public interface ItemManager {

    /**
     * Returns list of all items inside
     */
    public List<Item> getItems();

    /**
     * Tries to find and reutnr item by its name
     */
    public Optional<Item> getItem(String name);

    /**
     * Add item to the collection
     */
    public boolean addItem(Item item);

    /**
     * Tries to find and return and remove item from the collection by its name
     */
    public Optional<Item> getAndRemoveItem(String name);

    /**
     * Set maximum number of items that can be present at one time in the collection
     */
    public ItemManager setCapacity(int capacity);

    /**
     * Returns number of items that can fit into the collection
     */
    public int getAvailibleSpace();

}
