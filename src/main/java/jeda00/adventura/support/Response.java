package jeda00.adventura.support;

/**
 * Response the game returns after executing any command
 */
public interface Response {

    /**
     * Message printed back to the user as a response to his latest action
     */
    public String getMessage();

}
