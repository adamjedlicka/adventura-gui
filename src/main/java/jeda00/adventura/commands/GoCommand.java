package jeda00.adventura.commands;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jeda00.adventura.commands.util.Argument;
import jeda00.adventura.commands.util.Arguments;
import jeda00.adventura.support.Place;
import jeda00.adventura.support.Response;

/**
 * Goes to another room neighbouring with the current one
 */
public class GoCommand extends AbstractCommand {

    @Override
    public String getName() {
        return "go";
    }

    @Override
    public Response execute(List<String> arguments) {
        Place currentPlace = placeManager.getCurrentPlace();

        Optional<Place> target = currentPlace.getNeighbours().stream()
                .filter(place -> place.getName().equals(arguments.get(0))).findFirst();

        if (!target.isPresent()) {
            return response("You can't go there...");
        }

        Place place = target.get();

        placeManager.setCurrentPlace(place);

        StringBuilder sb = new StringBuilder();

        sb.append("You went to the ");
        sb.append(place.getName());
        sb.append(".");

        sb.append(" " + place.getEntryMessage());

        return response(sb.toString());
    }

    @Override
    public Arguments getArguments() {
        return new Arguments(
                new Argument("to").description("Where to go.").possibleValues(() -> placeManager.getCurrentPlace()
                        .getNeighbours().stream().map(place -> place.getName()).collect(Collectors.toList())));
    }

    @Override
    public String getHelp() {
        return "Moves the player to another location.";
    }

}
