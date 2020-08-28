package Exception;

/**
 * Represents the exception thrown if there are no description in the event.
 */
public class EventException extends DukeException {
    @Override
    public String toString() {
        String s = "OOPS!!! The description of a event cannot be empty.\n";
        return s;
    }
}
