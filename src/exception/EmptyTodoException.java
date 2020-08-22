package exception;

/**
 * Represents an exception stating the description of the <code>ToDo</code> to be added is empty.
 */
public class EmptyTodoException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + " " + "The description of a todo cannot be empty. Format: todo [description]";
    }
}
