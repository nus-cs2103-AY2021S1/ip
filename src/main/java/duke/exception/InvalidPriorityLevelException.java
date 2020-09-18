package duke.exception;

public class InvalidPriorityLevelException extends DukeException {
    public InvalidPriorityLevelException() {
        super("Oops that wasn't a valid priority level!\n");
    }
}
