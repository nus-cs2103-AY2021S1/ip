public class InvalidDescriptionException extends DukeException {
    public InvalidDescriptionException() {
        super("OOPS!!! The description of a todo cannot be empty");
    }
}
