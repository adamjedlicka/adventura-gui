package jeda00.adventura.support;

/**
 * Win condition which gets checked after every command execution
 */
public interface WinCondition {

    /**
     * Indicates whether the win condition was fullfiled and thus the game is won
     */
    public boolean isFulfilled();

    /**
     * Returns the winning message
     */
    public String getMessage();

}
