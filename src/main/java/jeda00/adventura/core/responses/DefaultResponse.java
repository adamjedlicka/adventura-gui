package jeda00.adventura.core.responses;

import jeda00.adventura.support.Response;

/**
 * Default response not indicating anything special
 */
public class DefaultResponse implements Response {

    private String message;

    public DefaultResponse(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
