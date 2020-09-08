package duke.command;

/**
 * Command is a class to be inherited by the various types of commands
 * that Duke is able to execute.
 */
public class Command {
    protected String response;

    /**
     * Initialise response with an empty String.
     */
    public Command() {
        response = "";
    }

    /**
     * Returns the response of the command executed.
     *
     * @return response after command executed.
     */
    public String getResponse() {
        return response;
    }

    public String execute() {
        return getResponse();
    }
}
