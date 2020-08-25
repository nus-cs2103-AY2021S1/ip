package duke.exception;

public class EventException extends DukeException {
    public EventException() {
        super("OOPS!!! The description or time of an event cannot be empty.");
    }
}
