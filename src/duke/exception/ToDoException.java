package duke.exception;

public class ToDoException extends DukeException {
    public ToDoException() {
        super("☹ OOPS!!! The description of a todo cannot be empty.");
    }
}
