package duke.exception;

// Signals any exception that occurs while the program runs.
public abstract class DukeException extends Exception {
    protected DukeException(String errorMessage) {
        super(errorMessage);
    }
}

