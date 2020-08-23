package duke.exception;

public class DukeException extends Exception {

    public DukeException(String msg) {
        super(msg);
    }

    public String getPrettyErrorMsg() {
        return "[ERROR]: " + getMessage();
    }
}
