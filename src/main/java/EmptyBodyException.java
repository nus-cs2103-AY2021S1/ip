public class EmptyBodyException extends DukeException {

    // Constructor
    public EmptyBodyException(String field, String type) {
        super(String.format("The %s of a %s cannot be empty.", field, type));
    }
}
