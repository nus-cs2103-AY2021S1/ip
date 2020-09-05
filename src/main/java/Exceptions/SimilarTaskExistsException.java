package exceptions;

public class SimilarTaskExistsException extends DukeException {

    /**
     * Initialize SimilarTaskExistsException class
     * @param message
     */
    public SimilarTaskExistsException(String message) {
        super(message);
    }
}
