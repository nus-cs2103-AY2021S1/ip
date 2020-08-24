package data.exception;

public class DukeIllegalCommandException extends DukeException {
    public DukeIllegalCommandException(String command) {
        super("I'm terribly sorry to inform you that " + command + " is not an unrecognizable command.");
    }
}
