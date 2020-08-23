package exceptions;

public class EmptyEventsException extends DukeException {

    public EmptyEventsException() {
        super("\u2639 OOPS!!! The description of a event cannot be empty.");
    }
}
