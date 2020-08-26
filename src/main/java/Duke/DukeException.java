package Duke;

public class DukeException extends Exception {
    DukeException(String message) {
        super(message);
    }

    DukeException(String message, Throwable err) {
        super(message, err);
    }
}
