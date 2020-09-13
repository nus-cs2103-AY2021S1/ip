package duke;

/**
 * Represents the part of Duke that deals with Duke's responses in the GUI.
 */
public class Response {
    /** The response message */
    private final String response;
    /** Indicates whether the GUI will terminate */
    private final boolean isExit;

    /**
     * Creates a new Response for the GUI.
     *
     * @param response the response message.
     * @param isExit the boolean variable that indicates whether or not Duke terminates.
     */
    public Response(String response, boolean isExit) {
        this.response = response;
        this.isExit = isExit;
    }

    public String getResponse() {
        return response;
    }

    public boolean isExit() {
        return isExit;
    }
}
