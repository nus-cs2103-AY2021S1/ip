/**
 * A kind of DukeException which specify invalid Todo description.
 */
public class InvalidTodoDescripDukeException extends DukeException{
    InvalidTodoDescripDukeException() {
        super(" ☹ OOPS!!! The description of a todo cannot be empty.");
    }
}

