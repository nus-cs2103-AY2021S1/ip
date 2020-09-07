package duke;
/**
 * Exception related to Duke application.
 */
public class DukeException extends Exception {

    /**
     * Constructor of DukeException.
     *
     * @param errorMessage Error message.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
