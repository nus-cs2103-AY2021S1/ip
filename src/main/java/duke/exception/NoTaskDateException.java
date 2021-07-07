package duke.exception;

/**
 * Represents an exception when user does not input any LocalDate when required.
 */
public class NoTaskDateException extends DukeException {

    /**
     * Constructs a NoTaskDateException.
     */
    public NoTaskDateException() {
        super("Please input a date in \"<dd/MM/yyyy>\" format!");
    }
}
