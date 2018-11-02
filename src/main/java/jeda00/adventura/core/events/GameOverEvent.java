package jeda00.adventura.core.events;

/**
 * Game was lost
 */
public class GameOverEvent {

    private String reason;

    public GameOverEvent(String reason) {
        this.reason = reason;
    }

    /**
     * Returns the reason why the game was lost
     */
    public String getReason() {
        return reason;
    }

}
