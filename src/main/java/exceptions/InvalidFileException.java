package exceptions;

/**
 * Thrown if the data.txt file cannot be found when required
 */
public class InvalidFileException extends DukeException {
    public InvalidFileException(String msg) {
        super(msg);
    }
}
