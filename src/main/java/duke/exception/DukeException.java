package duke.exception;

/**
 * General exception when an unknown command is used.
 */
public class DukeException extends Exception {
    /** Default constructor with a default error message. */
    public DukeException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                + "To see all commands, type \"help\"");
    }


    /**
     * Overloaded constructor that accepts a custom error message.
     * 
     * @param errorMessage Custom error message.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
