package jeda00.adventura.support;

/**
 * Item can be found in the rooms or picked up into the inventory
 */
public interface Item {

    /**
     * Returns name of the item
     */
    public String getName();

    /**
     * Indicates whether the item can be picked up
     */
    public boolean isMovable();

}
