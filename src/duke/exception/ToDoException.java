package duke.exception;

/**
 * The exception thrown when the user does not input any description
 * for the todo's task name.
 */
public class ToDoException extends DukeException {

    /**
     * Constructs a ToDoException with default message.
     * The message is "OOPS!!! The description of a todo cannot be empty."
     */
    public ToDoException() {
        super("OOPS!!! The description of a todo cannot be empty.");
    }
}
