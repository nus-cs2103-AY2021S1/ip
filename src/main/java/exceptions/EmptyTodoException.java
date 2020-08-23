package exceptions;
/**
 * EmptyTodoException class extends DukeException class
 * and handles the EmptyTodoException.
 * @author Maguire Ong
 */
public class EmptyTodoException extends DukeException {

    public EmptyTodoException() {
        super("\u2639 OOPS!!! The description of a todo cannot be empty.");
    }
}
