package duke.exceptions;

/** Represents the Duke exception which is thrown when the user does not input a valid dateTime. */
public class InvalidDateTimeException extends DukeException {

    /** Constructs a new InvalidDateTimeException object with the specified error message. */
    public InvalidDateTimeException() {
        super("Harh? The format of the date given is invalid.");
    }
}
