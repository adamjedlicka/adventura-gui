package jeda00.adventura.commands;

import java.util.List;

import jeda00.adventura.support.Item;
import jeda00.adventura.support.ItemManager;
import jeda00.adventura.support.Place;
import jeda00.adventura.support.Response;

/**
 * Looks around and reports what the player can see
 */
public class LookAroundCommand extends AbstractCommand {

    private StringBuilder response;

    private Place currentPlace;

    @Override
    public String getName() {
        return "look_around";
    }

    @Override
    public Response execute(List<String> arguments) {
        response = new StringBuilder();
        currentPlace = placeManager.getCurrentPlace();

        addCurrentPlace();
        addNeighbours();
        addItemsOnTheGround();

        return response(response.toString());
    }

    /**
     * Add information about current place
     */
    private void addCurrentPlace() {
        response.append("You are in a ");
        response.append(currentPlace.getName());
        response.append(".\n");
    }

    /**
     * Add information about neighbours of the current place
     */
    private void addNeighbours() {
        List<Place> neighbours = currentPlace.getNeighbours();
        if (neighbours.size() == 0) {
            return;
        }

        response.append("You can go to:");
        for (Place place : neighbours) {
            response.append(' ');
            response.append(place.getName());
        }

        response.append(".\n");
    }

    /**
     * Add information about items on the ground
     */
    private void addItemsOnTheGround() {
        ItemManager itemManager = currentPlace.getItemManager();
        if (itemManager.getItems().size() == 0) {
            return;
        }

        response.append("On the ground you can see:");
        for (Item item : itemManager.getItems()) {
            response.append(' ');
            response.append(item.getName());
        }

        response.append(".\n");
    }

    @Override
    public String getHelp() {
        return "Looks around the room and reports what the player can see.";
    }

}
