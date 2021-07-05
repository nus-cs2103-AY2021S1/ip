package duke;

/**
 * Represents exceptions specific to Duke
 */
public class DukeException extends Exception {

    /**
     * Constructs a duke exception
     * 
     * @param error The error message
     */
    public DukeException(String error) {
        super(error);
    }
}