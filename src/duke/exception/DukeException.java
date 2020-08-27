package duke.exception;

/**
 * DukeException is the super class of all other DukeExceptions.
 */
public abstract class DukeException extends RuntimeException {

    protected DukeException(String errMessage) {
        super(errMessage);
    }
}
