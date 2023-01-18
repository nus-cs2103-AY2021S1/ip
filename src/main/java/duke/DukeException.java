package duke;

/**
 * Contains error handling for Duke.
 */
public class DukeException extends Exception {

    /**
     * Create a new DukeException object.
     *
     * @param msg Error message.
     */
    DukeException(String msg) {
        super(msg);
    }

    /**
     * Overrides toString() method.
     *
     * @return String for error message.
     */
    @Override
    public String toString() {
        return "\uD83D\uDE40" + "OOPS!!! " + super.toString();
    }

}
