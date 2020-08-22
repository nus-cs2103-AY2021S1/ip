package duke.exceptions;

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

