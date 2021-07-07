package duke.exceptions;

/**
 * Encapsulates the exception where the user tries to create a timed task without the time.
 */

public class MissingTimeException extends DukeException {
    private String cmd;
    public MissingTimeException(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public String getMessage() {
        return "Please indicate the time for the " + cmd + ".";
    }
}

