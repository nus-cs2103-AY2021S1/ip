package duke.exception;

/**
 * Represents the exception that is thrown when the format of the input
 * is wrongly written
 */
public class WrongFormatException extends DukeException {

    /**
     * Constructor of the WrongFormatException Class.
     *
     * @param errorMessage error message to be thrown
     */
    public WrongFormatException(String errorMessage) {
        super(errorMessage);
    }

}
