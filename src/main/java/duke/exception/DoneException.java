package duke.exception;

public class DoneException extends DukeException {

    /**
     * Constructor of the DoneException Class.
     *
     * @param errorMessage error message to be thrown
     */
    public DoneException(String errorMessage) {
        super(errorMessage);
    }
}
