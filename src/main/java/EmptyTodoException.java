public class EmptyTodoException extends DukeException {
    EmptyTodoException() {
        super(String.format("%s OOPS!!! The description of a todo cannot be empty.",
                Character.toString(2639)));
    }
}
