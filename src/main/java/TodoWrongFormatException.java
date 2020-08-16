public class TodoWrongFormatException extends WrongFormatException {

    public TodoWrongFormatException() {
        super("todo");
    }

    @Override
    public String defaultErrorMessage() {
        return super.defaultErrorMessage() + "description of a task.";
    }
}
