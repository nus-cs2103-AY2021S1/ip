package duke.command;

/**
 * Represents the response from completing a command.
 */
public class CommandResponse {
    /** The response in string representation. */
    private String response;
    /** Indicates if the command response is an exit response. */
    private boolean isExit;

    /**
     * Creates a new command response with the specified string response and a boolean to indicate if the
     * response is an exit response.
     *
     * @param response The string response of this command response.
     * @param isExit Indicates if the command response is an exit command response.
     */
    public CommandResponse(String response, boolean isExit) {
        this.response = response;
        this.isExit = isExit;
    }

    /**
     * Gets the response in string representation.
     *
     * @return The response in string representation.
     */
    public String getResponse() {
        return this.response;
    }

    /**
     * Indicates if the response is an exit response.
     *
     * @return A boolean to indicate if the response is an exit response.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
