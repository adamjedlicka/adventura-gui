package jeda00.adventura.core;

import java.util.Map;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

import jeda00.adventura.support.Command;
import jeda00.adventura.support.CommandManager;

/**
 * Default implementation of command manager interface
 */
public class DefaultCommandManager implements CommandManager {

    private Map<String, Command> commands;

    public DefaultCommandManager() {
        commands = new HashMap<>();
    }

    @Override
    public void registerCommand(Command command) {
        commands.put(command.getName(), command);
    }

    @Override
    public Optional<Command> getCommand(String name) {
        Command commandFactory = commands.get(name.toLowerCase());

        if (commandFactory == null) {
            return Optional.empty();
        }

        return Optional.of(commandFactory);
    }

    @Override
    public Collection<Command> getCommands() {
        return commands.values();
    }

}
