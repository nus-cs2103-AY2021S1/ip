package duke.exceptions;

public class DukeException extends Exception {
    @Override
    public String getMessage() {
        return "Duke has encountered an error.";
    }
}

