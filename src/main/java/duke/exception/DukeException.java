package duke.exception;

public class DukeException extends RuntimeException {
    public DukeException(String msg) {
        super(":( \n\tI dont understand this... \n\t" + msg);
    }
}
