package duke.exception;

public class EmptyTodoException extends Exception {
    public EmptyTodoException() {
        super("\uD83D\uDE41 OOPS! The description of a todo cannot be empty.");
    }
    @Override
    public String toString() {
        return super.getMessage();
    }
}
