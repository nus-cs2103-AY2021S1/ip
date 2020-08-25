package duke.exception;

/**
 * Thrown when the file format of the CSV is not properly formatted.
 */
public class InvalidFileFormatException extends DukeException {

    /**
     * Initializes the InvalidFileFormatException.
     */
    public InvalidFileFormatException() {
        super("CSV file is poorly formatted!");
    }
}
