package jeda00.adventura.commands;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jeda00.adventura.commands.util.Argument;
import jeda00.adventura.commands.util.Arguments;
import jeda00.adventura.core.events.ItemPickedUpEvent;
import jeda00.adventura.support.Item;
import jeda00.adventura.support.ItemManager;
import jeda00.adventura.support.PlaceManager;
import jeda00.adventura.support.Response;
import jeda00.container.Container;
import jeda00.eventbus.EventBus;

/**
 * Picks up item from the ground
 */
public class PickUpCommand extends AbstractCommand {

    private final PlaceManager placeManager = Container.resolve(PlaceManager.class);

    @Override
    public String getName() {
        return "pick_up";
    }

    @Override
    public Response execute(List<String> arguments) {
        if (game.getInventory().getAvailibleSpace() <= 0) {
            return response("Your inventory is full.");
        }

        ItemManager from = placeManager.getCurrentPlace().getItemManager();
        Optional<Item> optional = from.getAndRemoveItem(arguments.get(0));

        if (!optional.isPresent()) {
            return response("There is no such thing.");
        }

        Item item = optional.get();

        if (!item.isMovable()) {
            return response("This is too heavy. You can't pick it up.");
        }

        // First if in the begining of this method should take care of this but one
        // cannot predict the unpredictable.
        if (!game.getInventory().addItem(item)) {
            if (!from.addItem(item)) {
                return response("You cannot pick this item and at the same time you cannot place it back where it was. "
                        + "You somehow managed to totally break this game. Good job!");
            }
            return response("Your inventory is full.");
        }

        Container.resolve(EventBus.class).emit(new ItemPickedUpEvent(item, placeManager.getCurrentPlace()));

        return response("You picked up " + item.getName() + ".");
    }

    @Override
    public Arguments getArguments() {
        return new Arguments(new Argument("what").description("What to pick up.")
                .possibleValues(() -> placeManager.getCurrentPlace().getItemManager().getItems().stream()
                        .map(item -> item.getName()).collect(Collectors.toList())));
    }

    @Override
    public String getHelp() {
        return "Picks up the item from the ground, if the item can be picked up.";
    }

}
