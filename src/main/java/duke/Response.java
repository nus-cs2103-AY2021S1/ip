package duke;

import duke.command.Command;

/**
 * Returns responses to show to the user in the GUI version of Duke.
 */
public class Response {
    /** The response to show to the user */
    private String responseString;

    /** The command that the user entered that warrants this response */
    private Command command;

    /**
     * Creates a Response object.
     *
     * @param responseString The response to show to the user.
     * @param command The command that the user entered that warrants this response.
     */
    public Response(String responseString, Command command) {
        this.responseString = responseString;
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    public String getResponseString() {
        return responseString;
    }
}
