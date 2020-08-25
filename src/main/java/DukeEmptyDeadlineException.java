/**
 * Thrown to indicate that the user has put in an empty deadline
 */

public class DukeEmptyDeadlineException extends DukeException {

    /**
     * Constructs a DukeEmptyDeadlineException with a relevant detail message.
     *
     * @param s Represents the error message.
     */
    DukeEmptyDeadlineException(String s) {
        super(s);
    }
}
