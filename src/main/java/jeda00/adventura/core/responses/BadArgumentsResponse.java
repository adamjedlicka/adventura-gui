package jeda00.adventura.core.responses;

import jeda00.adventura.support.Command;
import jeda00.adventura.support.Response;

/**
 * Player entered bad number of arguments
 */
public class BadArgumentsResponse implements Response {

    private Command command;

    public BadArgumentsResponse(Command command) {
        this.command = command;
    }

    @Override
    public String getMessage() {
        StringBuilder sb = new StringBuilder();

        sb.append("Bad number of arguments. Usage:\n");
        sb.append(command.getName());
        sb.append(" ");
        sb.append(command.getArguments().toString());

        return sb.toString();
    }

}
