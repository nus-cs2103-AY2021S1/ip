public class InvalidInputException extends DukeException {

    public InvalidInputException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "OOPS!!! The description of a todo cannot be empty.";
    }
}
