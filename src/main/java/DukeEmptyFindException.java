/**
 * Thrown to indicate that the user has put in an empty find.
 */
public class DukeEmptyFindException extends DukeException {

    /**
     * Constructs a DukeEmptyFindException with a relevant detail message.
     *
     * @param s Represents the error message.
     */
    DukeEmptyFindException(String s) {
        super("OOPS!!! Empty find body!");
    }
}
