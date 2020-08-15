public class EmptyMessageException extends DukeException {
    public EmptyMessageException(boolean isCommand, String message) {
        super(isCommand
                ? String.format("     ☹ OOPS!!! The description of a %s"
                        + " cannot be empty.", message)
                : message);
    }
}
