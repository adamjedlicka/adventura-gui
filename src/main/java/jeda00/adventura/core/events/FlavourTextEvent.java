package jeda00.adventura.core.events;

/**
 * New flavour was generated and should by added to the output
 */
public class FlavourTextEvent {

    private String message;

    public FlavourTextEvent(String message) {
        this.message = message;
    }

    /**
     * Returns the message of the flavout text
     */
    public String getMessage() {
        return message;
    }

}
