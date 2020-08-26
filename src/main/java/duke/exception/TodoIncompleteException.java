package duke.exception;

public class TodoIncompleteException extends DukeException {

    @Override
    public String getMessage() {
        return " Oh no! Please specify the description of a todo.";
    }
}
