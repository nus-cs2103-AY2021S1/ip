package duke;

/**
 * Exception that Duke throws when there are errors in the program.
 */
public class DukeException extends Exception {

    /**
     * Exception thrown to user when Duke encounters errors.
     *
     * @param errorMessage the details of the error thrown.
     */
    public DukeException(String errorMessage) {
       super("☹ OOPS!!! " + errorMessage);
    }
}
