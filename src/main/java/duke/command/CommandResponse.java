package duke.command;

/**
 * The type Command response.
 */
public class CommandResponse {
    /**
     * The Message.
     */
    private final String message;
    /**
     * True if command executed is a bye command, false otherwise.
     */
    private final boolean isExit;
    /**
     * True if there is no response, false otherwise.
     */
    private final boolean isEmpty;

    /**
     * Instantiates a new empty Command response.
     */
    public CommandResponse() {
        this.message = "";
        this.isExit = false;
        this.isEmpty = true;
    }

    /**
     * Instantiates a new Command response.
     *
     * @param message the message.
     * @param isExit  the is exit.
     */
    public CommandResponse(String message, boolean isExit) {
        this.message = message;
        this.isExit = isExit;
        this.isEmpty = false;
    }

    /**
     * Returns message.
     *
     * @return the message.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Returns true if command executed is a bye command, false otherwise.
     *
     * @return the boolean.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Returns true if there is no response, false otherwise.
     *
     * @return the boolean.
     */
    public boolean isEmpty() {
        return this.isEmpty;
    }
}
