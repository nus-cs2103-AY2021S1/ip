package duke.exception;

/**
 * Thrown when the task type is unknown. e.g. Can be thrown after modifying
 * auto saved task data.
 */
public class UnknownTaskTypeException extends DukeException {

    public UnknownTaskTypeException(String errMessage) {
        super(errMessage);
    }
}
