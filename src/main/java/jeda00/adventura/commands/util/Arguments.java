package jeda00.adventura.commands.util;

import java.util.Arrays;
import java.util.List;

/**
 * Collection of arguments
 */
public class Arguments {

    private List<Argument> arguments;

    public Arguments(Argument... arguments) {
        this.arguments = Arrays.asList(arguments);
    }

    /**
     * Returns list of arguments
     */
    public List<Argument> getArguments() {
        return arguments;
    }

    /**
     * Returns minimum number of arguments for the corresponding command
     */
    public int getMin() {
        return arguments.size();
    }

    /**
     * Reutnrs maximum number of arguments for the correspondign commadn
     */
    public int getMax() {
        return arguments.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Argument argument : arguments) {
            sb.append('[');
            sb.append(argument.getName());
            sb.append(']');
        }

        sb.append("\n");

        for (Argument argument : arguments) {
            sb.append(" - ");
            sb.append(argument.toString());
            sb.append("\n");
        }

        // Remove the trailing newline
        sb.setLength(sb.length() - 1);

        return sb.toString();
    }

}
