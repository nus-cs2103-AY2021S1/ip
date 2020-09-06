/**
 * Thrown to indicate that the user has put in an invalid tag number.
 */
public class DukeInvalidTagNumException extends DukeException {

    /**
     * Constructs a DukeInvalidTagNumException with a relevant detail message.
     *
     * @param s Represents the error message.
     */
    DukeInvalidTagNumException(String s) {
        super("OOPS!!! You have keyed in an invalid tag number.");
    }
}
