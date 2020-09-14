/**
 * The program runs into a invalid Todo input exception.
 */
public class InvalidTodoException extends DukeException {
    public InvalidTodoException() {
        super(" OOPS!!! The description of a todo cannot be empty.");
    }
}
