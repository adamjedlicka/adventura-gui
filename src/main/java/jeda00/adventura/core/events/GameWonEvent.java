package jeda00.adventura.core.events;

/**
 * Game was successfully won
 */
public class GameWonEvent {

    private String reason;

    public GameWonEvent(String reason) {
        this.reason = reason;
    }

    /**
     * Returns the reason why the game was successfully won
     */
    public String getReason() {
        return reason;
    }

}
