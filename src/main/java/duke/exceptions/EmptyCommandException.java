package duke.exceptions;

/**
 * Encapsulates an exception where the user tries to create a task without description.
 */
public class EmptyCommandException extends DukeException {
    private String cmd;
    public EmptyCommandException (String cmd) {
        this.cmd = cmd;
    }

    @Override
    public String getMessage() {
        return "Sorry, the description of a " + cmd + " cannot be empty.";
    }
}

