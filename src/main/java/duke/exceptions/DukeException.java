package duke.exceptions;

public class DukeException extends Exception {
    @Override
    public String getMessage() {
        return "duke.Duke has encountered an error.";
    }
}

