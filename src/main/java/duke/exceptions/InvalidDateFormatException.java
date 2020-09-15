package duke.exceptions;

/**
 * Thrown when the user input a wrongly formatted Date.
 */
public class InvalidDateFormatException extends DukeException {
    public InvalidDateFormatException() {
        super("\u2639 OOPS!!! Looks like the date you inputted is invalid");
    }
}
