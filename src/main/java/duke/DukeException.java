package duke;

/**
 * Encompasses exceptions specific to Duke
 */
public class DukeException extends Exception {
    /**
     * Constructor
     * @param errorMsg Error message to print
     */
    public DukeException(String errorMsg) {
        super("OOPS!!! " + errorMsg);
    }
}