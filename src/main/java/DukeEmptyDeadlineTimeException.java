/**
 * Thrown to indicate that the user has put in an empty deadline Time.
 */
public class DukeEmptyDeadlineTimeException extends DukeException {

    /**
     * Constructs a DukeEmptyDeadlineTimeException with a relevant detail message.
     *
     * @param s Represents the error message.
     */
    DukeEmptyDeadlineTimeException(String s) {
        super("OOPS!!! The description of a deadline time cannot be empty.");
    }
}
