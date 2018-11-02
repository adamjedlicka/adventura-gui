package jeda00.adventura.support;

/**
 * Game is main entry point
 */
public interface Game {

    /**
     * Executes the command and return result of this execution.
     */
    public Response execute(String input);

    /**
     * Returns the player inventory.
     */
    public ItemManager getInventory();

    /**
     * Indicates whether the game is currently running.
     */
    public boolean isRunning();

    /**
     * Returns the welcome message for the player;
     */
    public String getWelcomeMessage();

    /**
     * Returns the message for the user after the game is exited
     */
    public String getExitMessage();

}
