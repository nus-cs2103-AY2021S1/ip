public class EventEmptyBodyException extends EmptyBodyException {
    EventEmptyBodyException() {}
    @Override
    public String toString() {
        return "☹ OOPS!!! The description of an event cannot be empty.";
    }
}