package exception;

/**
 * Triggers when there is either something wrong with
 * the previous save file or there is an issue writing
 * or saving the new save file.
 */
public class InvalidSaveFileException extends DukeException {

    /**
     * Initialises the exception object with a warning
     * to the user that the saving function has an issue.
     *
     * @param message Contains a more detailed error description.
     */
    public InvalidSaveFileException(String message) {
        super(message);
    }
}
