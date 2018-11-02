package jeda00.adventura.commands;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jeda00.adventura.commands.util.Argument;
import jeda00.adventura.commands.util.Arguments;
import jeda00.adventura.core.events.ItemPutDownEvent;
import jeda00.adventura.support.Game;
import jeda00.adventura.support.Item;
import jeda00.adventura.support.ItemManager;
import jeda00.adventura.support.Response;
import jeda00.container.Container;
import jeda00.eventbus.EventBus;

/**
 * Put down item from the inventory to the ground
 */
public class PutDownCommand extends AbstractCommand {

    private final Game game = Container.resolve(Game.class);

    public String getName() {
        return "put_down";
    }

    @Override
    public Response execute(List<String> arguments) {
        ItemManager to = placeManager.getCurrentPlace().getItemManager();
        if (to.getAvailibleSpace() <= 0) {
            return response("There is no more space tu put down the item.");
        }

        Optional<Item> optional = game.getInventory().getAndRemoveItem(arguments.get(0));
        if (!optional.isPresent()) {
            return response("You have no such item in your inventory.");
        }

        Item item = optional.get();

        if (!to.addItem(item)) {
            return response("You were unable to put down the item.");
        }

        Container.resolve(EventBus.class).emit(new ItemPutDownEvent(item, placeManager.getCurrentPlace()));

        return response("You successfully put down the " + item.getName() + ".");
    }

    @Override
    public Arguments getArguments() {
        return new Arguments(new Argument("what").description("What to put down.").possibleValues(() -> game
                .getInventory().getItems().stream().map(item -> item.getName()).collect(Collectors.toList())));
    }

    @Override
    public String getHelp() {
        return "Puts down the item from inventory on the ground.";
    }

}
