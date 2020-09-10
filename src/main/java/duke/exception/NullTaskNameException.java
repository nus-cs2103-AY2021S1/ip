package duke.exception;

public class NullTaskNameException extends DukeException {

    public NullTaskNameException(String command) {
        super(command);
    }

    @Override
    public String toString() {
        return "Please name the " + command + " item!";
    }
}
