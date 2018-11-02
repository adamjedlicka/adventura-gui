package jeda00.adventura.core.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for parsing the command in string format
 */
public class CommandParser {

    private String input;

    private String command;

    private List<String> arguments;

    public CommandParser(String input) {
        String[] array = input.trim().split("\\s+");

        this.input = input;
        this.command = array[0];
        this.arguments = new ArrayList<>();

        for (int i = 1; i < array.length; i++) {
            arguments.add(array[i]);
        }
    }

    /**
     * Returns the whole unchanged input
     */
    public String getInput() {
        return input;
    }

    /**
     * Returns name of the command
     */
    public String getCommand() {
        return command;
    }

    /**
     * Returns list of arguments for the command
     */
    public List<String> getArguments() {
        return arguments;
    }

}
