package duke.exception;

public class DukeException extends Exception {
    DukeException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
