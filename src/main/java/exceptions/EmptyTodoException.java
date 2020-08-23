package exceptions;

public class EmptyTodoException extends DukeException {

    public EmptyTodoException() {
        super("\u2639 OOPS!!! The description of a todo cannot be empty.");
    }
}
