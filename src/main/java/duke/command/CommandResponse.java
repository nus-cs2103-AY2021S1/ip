package duke.command;

/**
 * Represents a response from the application after executing a Command.
 */
@SuppressWarnings("checkstyle:Regexp")
public class CommandResponse {

    /** Message to be shown to user */
    private final String responseMessage;

    /** Indicates if the program should exit */
    private final boolean shouldExit;

    /**
     * Constructs a <code>CommandResponse</code> object.
     *
     * @param responseMessage Message to be shown to user.
     * @param shouldExit Indicates if the program should exit.
     */
    public CommandResponse(String responseMessage, boolean shouldExit) {
        this.responseMessage = responseMessage;
        this.shouldExit = shouldExit;
    }

    /**
     * Retrieves message to be shown to user.
     *
     * @return responseMessage.
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     * Retrieves boolean value that indicates if the program should exit.
     *
     * @return shouldExit.
     */
    public boolean getShouldExit() {
        return shouldExit;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof CommandResponse) {
            CommandResponse cr = (CommandResponse) obj;
            return cr.responseMessage.equals(this.responseMessage) && cr.shouldExit == this.shouldExit;
        } else {
            return false;
        }
    }
}

