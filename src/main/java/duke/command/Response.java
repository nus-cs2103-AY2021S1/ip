package duke.command;

import duke.tools.Format;

/**
 * Make response to the user's input.
 */
public class Response {
    protected String response;

    /**
     * Constructs a Response.
     *
     * @param response A string which
     *                 is the content of
     *                 response.
     */
    public Response(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return new Format<>(response).toString();
    }
}
