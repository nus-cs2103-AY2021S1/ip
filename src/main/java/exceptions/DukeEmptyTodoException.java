package exceptions;

public class DukeEmptyTodoException extends DukeException {

    public DukeEmptyTodoException() {
        super("Oops! Todo cannot have an empty message!");
    }
}
