package exception;

/**
 * Exception that is thrown when there is a problem with file
 */
public class DukeFileException extends DukeException {
    public DukeFileException() {
        super("Error occurred when reading the File");
    }
}
