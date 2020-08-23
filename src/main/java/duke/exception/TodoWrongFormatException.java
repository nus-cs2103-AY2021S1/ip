package duke.exception;

public class TodoWrongFormatException extends WrongFormatException {

    public TodoWrongFormatException() {
        super("todo");
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "description of a task.";
    }
}
