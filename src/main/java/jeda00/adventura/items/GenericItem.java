package jeda00.adventura.items;

/**
 * Generic item with no special behaviours
 */
public class GenericItem extends AbstractItem {

    private final String name;

    private final boolean isMovable;

    public GenericItem(String name, boolean isMovable) {
        this.name = name;
        this.isMovable = isMovable;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isMovable() {
        return isMovable;
    }

}
