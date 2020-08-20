public class InvalidDescriptionException extends DukeException {

    public InvalidDescriptionException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Description is invalid. Please specify with more details.";
    }
}