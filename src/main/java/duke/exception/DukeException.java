package duke.exception;

public class DukeException extends Exception {
    public DukeException(String msg) {
        super(msg);
    }

    public static DukeException storageIOException(String message) {
        return new DukeException("Error! " + message);
    }
}
