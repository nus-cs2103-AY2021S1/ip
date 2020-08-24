package Duke;

public class DukeException extends Exception {
    /**
     * The constructor for a DukeException, an exception exclusive to the Duke package.
     *
     * @param errorMessage The desired error message to be displayed.
     */
    public DukeException (String errorMessage) {
        super(errorMessage);
    }
}
