package jeda00.adventura.core.responses;

import jeda00.adventura.commands.util.Argument;
import jeda00.adventura.support.Response;

/**
 * One of the argument value entered by the player was not in the list of
 * possible values for said argument
 */
public class BadArgumentValueResponse implements Response {

    public Argument argument;

    public BadArgumentValueResponse(Argument argument) {
        this.argument = argument;
    }

    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder();

        sb.append("Bad value of argument: ");
        sb.append(argument.getName());
        sb.append(".\n");
        sb.append("Possible values: ");
        sb.append(argument.getPossibleValues());

        return sb.toString();
    }

}
