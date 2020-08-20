public class EmptyToDoException extends DukeException {
    public EmptyToDoException() {
        super("☹ OOPS!!! The description of a todo cannot be empty.");
    }
}
