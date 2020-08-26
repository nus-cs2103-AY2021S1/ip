public class InvalidDescriptionException extends DukeException {
    public InvalidDescriptionException() {
        super("OOPS!!! The description of a task cannot be empty");
    }
}
