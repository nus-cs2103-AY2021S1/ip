package duke.command;

/**
 * Represents the command response after executing a command.
 */
public class CommandResponse {
    /** The string representation of response. */
    private final String response;
    /** Indicates whether the command response is an exit response. */
    private final boolean isExit;

    /**
     * Makes a command response with the string response and boolean isExit parameters.
     *
     * @param response is the string representation of this response.
     * @param isExit indicates whether the response is an exit command response.
     */
    public CommandResponse(String response, boolean isExit) {
        this.response = response;
        this.isExit = isExit;
    }

    /**
     * Retrieves the string response.
     *
     * @return the string response.
     */
    public String getResponse() {
        return this.response;
    }

    /**
     * Gives the boolean of isExit.
     *
     * @return either true or false.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
