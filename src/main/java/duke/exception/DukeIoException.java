package duke.exception;

/**
 * Represents an IOException while running Duke.
 */
public class DukeIoException extends DukeException {

    public DukeIoException(String msg) {
        super(msg);
    }

    @Override
    public String getPrettyErrorMsg() {
        return "[IO ERROR]: " + getMessage();
    }
}
