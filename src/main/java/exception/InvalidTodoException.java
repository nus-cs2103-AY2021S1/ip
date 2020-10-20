package exception;

public class InvalidTodoException extends DukeErrorException {

    public InvalidTodoException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "\n     Usage: todo <todo description>";
    }
}
