package jeda00.adventura.places;

import java.util.ArrayList;
import java.util.List;

import jeda00.adventura.support.ItemManager;
import jeda00.adventura.support.Place;
import jeda00.container.Container;

/**
 * Abstract place with helper methods for extending
 */
public abstract class AbstractPlace implements Place {

    protected ItemManager itemManager;

    protected List<Place> neighbours;

    public AbstractPlace() {
        itemManager = Container.resolve(ItemManager.class);
        neighbours = new ArrayList<>();
    }

    @Override
    public String getEntryMessage() {
        return "";
    }

    @Override
    public List<Place> getNeighbours() {
        return neighbours;
    }

    @Override
    public ItemManager getItemManager() {
        return itemManager;
    }

}
