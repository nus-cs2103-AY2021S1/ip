package duke.exception;

public abstract class DukeException extends RuntimeException {

    protected DukeException(String errMessage) {
        super(errMessage);
    }
}
