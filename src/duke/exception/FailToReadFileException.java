package duke.exception;

/**
 * The exception thrown when the Scanner or FileWriter class
 * fails to read the file in the hard disk.
 */
public class FailToReadFileException extends DukeException {

    /**
     * Constructs a FailToReadFileException with default message.
     * The message is "Something went wrong when reading the storage file! Please try again."
     */
    public FailToReadFileException() {
        super("Something went wrong when reading the storage file! Please try again.");
    }
}
