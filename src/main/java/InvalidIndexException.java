public class InvalidIndexException extends DukeException {
    public InvalidIndexException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "OOPS!!! Please enter a valid index!";
    }
}
