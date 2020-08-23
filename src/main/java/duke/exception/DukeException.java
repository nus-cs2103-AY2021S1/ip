package duke.exception;

public class DukeException extends Exception {
    String message;
    DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.message;
    }
}
