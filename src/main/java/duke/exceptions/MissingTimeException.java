package duke.exceptions;

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

