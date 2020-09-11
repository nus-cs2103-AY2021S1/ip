package duke.exception;

/**
 * Represent the base class of all the exceptions
 */
public class DukeException extends Exception {

    /**
     * Constructor of the DukeException Class.
     *
     * @param errorMessage error message to be thrown
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

}
