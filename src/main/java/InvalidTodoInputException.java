public class InvalidTodoInputException extends DukeException {

    public InvalidTodoInputException() {
        super("☹ OOPS!!! The description of a todo cannot be empty.");
    }
}
