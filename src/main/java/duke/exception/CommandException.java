package duke.exception;

/**
 * Represents the exception that is thrown when the input is completely
 * not recognised
 */
public class CommandException extends DukeException {

    /**
     * Constructor of the CommandException Class
     *
     * @param errorMessage error message to be thrown
     */
    public CommandException(String errorMessage) {
        super(errorMessage);
    }
}
