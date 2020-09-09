package duke;

public class DukeException extends Exception {
    /**
     * The constructor for a DukeException, an exception exclusive to the duke.Duke package.
     *
     * @param errorMessage The desired error message to be displayed.
     */
    public DukeException (String errorMessage) {
        super(errorMessage);
        assert errorMessage.length() > 0 : "Empty Duke Error Message";
    }
}
