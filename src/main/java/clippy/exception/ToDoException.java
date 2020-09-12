package clippy.exception;

public class ToDoException extends ClippyException {
    public ToDoException() {
        super("☹ OOPS!!! The description of a todo cannot be empty.");
    }
}
