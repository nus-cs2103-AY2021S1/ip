package duke.exceptions;

/**
 * Encapsulates all possible exceptions which occur when Duke is runnning.
 */

public class DukeException extends Exception {
    @Override
    public String getMessage() {
        return "Duke has encountered an error.";
    }
}

