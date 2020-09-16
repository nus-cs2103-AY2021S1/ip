package duke;

import duke.command.Command;

/**
 * Returns responses to show to the user in the GUI version of Duke.
 */
public class Response {
    /** The response to show to the user */
    private String responseString;

    /** The command that the user entered that created this response */
    private Command command;

    /**
     * Creates a Response object.
     *
     * @param responseString The response to show to the user.
     * @param command The command that the user entered that created this response.
     */
    public Response(String responseString, Command command) {
        this.responseString = responseString;
        this.command = command;
    }

    /**
     * Gets the command that the user entered that created this response.
     *
     * @return The command that the user entered that created this response.
     */
    public Command getCommand() {
        return command;
    }

    /**
     * Gets the response to show to the user.
     *
     * @return The response to show to the user.
     */
    public String getResponseString() {
        return responseString;
    }
}
