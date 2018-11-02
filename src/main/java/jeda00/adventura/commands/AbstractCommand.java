package jeda00.adventura.commands;

import jeda00.adventura.commands.util.Arguments;
import jeda00.adventura.core.responses.DefaultResponse;
import jeda00.adventura.support.Command;
import jeda00.adventura.support.Game;
import jeda00.adventura.support.PlaceManager;
import jeda00.adventura.support.Response;
import jeda00.container.Container;

/**
 * Abstract command with helper methods
 */
public abstract class AbstractCommand implements Command {

    protected Game game;

    protected PlaceManager placeManager;

    public AbstractCommand() {
        game = Container.resolve(Game.class);
        placeManager = Container.resolve(PlaceManager.class);
    }

    /**
     * Returns default response. Helper for better fluent syntax
     */
    protected Response response(String message) {
        return new DefaultResponse(message);
    }

    @Override
    public Arguments getArguments() {
        return new Arguments();
    }

}
