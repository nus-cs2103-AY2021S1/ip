package duke.exception;

/**
 * Thrown when the command entered does not follow the correct format.
 */
public class InvalidFormatException extends DukeException{

    public InvalidFormatException(String missing) {
        super(missing + " is missing");
    }

    public String toString() {
        return getMessage();
    }
}
