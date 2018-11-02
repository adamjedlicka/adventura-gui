package jeda00.adventura.support;

import java.util.List;

import jeda00.adventura.commands.util.Arguments;

/**
 * Command is behaviour which the game can execute
 */
public interface Command {

    /**
     * Returns name of the commadn
     */
    public String getName();

    /**
     * Executes the command with given arguments
     *
     * @param arguments Arguments for the commadn
     */
    public Response execute(List<String> arguments);

    /**
     * Returns description of accepted arguments
     */
    public Arguments getArguments();

    /**
     * Returns help text of the command
     */
    public String getHelp();

}
