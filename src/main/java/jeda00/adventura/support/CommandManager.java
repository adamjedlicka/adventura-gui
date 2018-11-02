package jeda00.adventura.support;

import java.util.Collection;
import java.util.Optional;

/**
 * manages commands
 */
public interface CommandManager {

    /**
     * Register new command for use in game
     *
     * @param command Command instance to be registered
     */
    public void registerCommand(Command command);

    /**
     * Tries to find and return registered command by its name
     *
     * @param name Name of the command it tries to find
     * @return Option of command if it found or not
     */
    public Optional<Command> getCommand(String name);

    /**
     * Returns the list of all commands
     *
     * @return Collection of all registered commands
     */
    public Collection<Command> getCommands();

}
