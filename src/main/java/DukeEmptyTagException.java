/**
 * Thrown to indicate that the user has put in an empty Tag message.
 */
public class DukeEmptyTagException extends DukeException {

    /**
     * Constructs a DukeEmptyTagException with a relevant detail message.
     *
     * @param s Represents the error message.
     */
    DukeEmptyTagException(String s) {
        super("OOPS!!! You have keyed in an empty tag.");
    }
}
