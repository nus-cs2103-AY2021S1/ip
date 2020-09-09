package duke;

/**
 * Represents the part of Duke that deals with Duke's responses in the GUI.
 */
public class Response {
    private final String response;
    private final boolean isExit;

    /**
     * Creates a new Response for the GUI.
     *
     * @param response the response message.
     * @param isExit the boolean variable that states whether or not an exit command was entered.
     */
    public Response(String response, boolean isExit) {
        this.response = response;
        this.isExit = isExit;
    }

    /**
     * Retrieves the response message.
     *
     * @return the response message.
     */
    public String getResponse() {
        return response;
    }

    /**
     * Indicates whether or not an exit command was entered.
     *
     * @return true if an exit command was entered or false otherwise.
     */
    public boolean isExit() {
        return isExit;
    }
}
