package jeda00.adventura;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import jeda00.adventura.items.GenericItem;
import jeda00.adventura.support.Item;
import jeda00.adventura.support.ItemManager;
import jeda00.container.Container;

public class ItemManagerTest extends DefaultTestCase {

    @Test
    public void testCapacity() {
        ItemManager im = Container.resolve(ItemManager.class).setCapacity(2);

        assertEquals(2, im.getAvailibleSpace());

        assertTrue(im.addItem(new GenericItem("Item 1", true)));
        assertTrue(im.addItem(new GenericItem("Item 2", true)));
        assertFalse(im.addItem(new GenericItem("Item 3", true)));
    }

    @Test
    public void testRemovingItem() {
        ItemManager im = Container.resolve(ItemManager.class).setCapacity(2);

        Item item1 = new GenericItem("Item 1", true);
        Item item2 = new GenericItem("Item 2", true);

        im.addItem(item1);
        im.addItem(item2);

        Optional<Item> item3 = im.getAndRemoveItem("Item 1");

        assertTrue(item3.isPresent());
        assertEquals(item1, item3.get());
        assertEquals(1, im.getItems().size());
    }

    @Test
    public void testGettingItem() {
        ItemManager im = Container.resolve(ItemManager.class).setCapacity(2);

        Item item = new GenericItem("Item good", true);

        im.addItem(item);

        Optional<Item> itemBad = im.getItem("Item bad");
        assertFalse(itemBad.isPresent());

        Optional<Item> itemGood = im.getItem("Item good");
        assertTrue(itemGood.isPresent());
        assertEquals(item, itemGood.get());
    }

    @Test
    public void testAddingItemsTheWrongWay() {
        thrown.expect(UnsupportedOperationException.class);

        ItemManager im = Container.resolve(ItemManager.class).setCapacity(2);

        Item item = new GenericItem("Item good", true);

        im.getItems().add(item);
    }

}
