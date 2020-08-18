public class InvalidIndexException extends DukeException {
    public InvalidIndexException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "OOPS!!! The description of a todo cannot be empty.";
    }
}
