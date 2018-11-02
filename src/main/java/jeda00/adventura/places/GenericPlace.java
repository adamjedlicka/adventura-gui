package jeda00.adventura.places;

/**
 * Generic place with no special behaviour
 */
public class GenericPlace extends AbstractPlace {

    private String name;

    public GenericPlace(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}
