package duke.exception;

public class SomethingWentWrongException extends DukeException {
    public SomethingWentWrongException() {
        super("Something went wrong here.");
    }
}
