package duke.exception;

public class DukeIOException extends DukeException {
    public DukeIOException(String msg) {
        super(msg);
    }

    @Override
    public String getPrettyErrorMsg() {
        return "[IO ERROR]: " + getMessage();
    }
}
