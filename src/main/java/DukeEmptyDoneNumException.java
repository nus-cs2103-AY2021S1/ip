/**
 * Thrown to indicate that the user has put in an invalid done number.
 */
public class DukeEmptyDoneNumException extends DukeException {

    /**
     * Constructs a DukeEmptyDoneNumException with a relevant detail message.
     *
     * @param s Represents the error message.
     */
    DukeEmptyDoneNumException(String s) {
        super("OOPS!!! You have keyed in a done command without a number.");
    }
}
