package jeda00.adventura.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import jeda00.adventura.support.Item;
import jeda00.adventura.support.ItemManager;

/**
 * Default implementation of ItemManager interface
 */
public class DefaultItemManager implements ItemManager {

    private List<Item> items;

    private int capacity;

    public DefaultItemManager() {
        items = new ArrayList<>();
        capacity = Integer.MAX_VALUE;
    }

    @Override
    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    @Override
    public Optional<Item> getItem(String name) {
        return items.stream().filter(it -> it.getName().equals(name)).findFirst();
    }

    @Override
    public boolean addItem(Item item) {
        if (items.size() >= capacity) {
            return false;
        }

        items.add(item);

        return true;
    }

    @Override
    public Optional<Item> getAndRemoveItem(String name) {
        Optional<Item> item = getItem(name);

        item.ifPresent(it -> items.remove(it));

        return item;
    }

    @Override
    public ItemManager setCapacity(int capacity) {
        this.capacity = capacity;

        return this;
    }

    @Override
    public int getAvailibleSpace() {
        return capacity - items.size();
    }

}
