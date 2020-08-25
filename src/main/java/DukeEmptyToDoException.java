
/**
 * Thrown to indicate that the user has put in an empty ToDo reminder.
 */
public class DukeEmptyToDoException extends DukeException {

    /**
     * Constructs a DukeEmptyToDoException with a relevant detail message.
     *
     * @param s Represents the error message.
     */
    DukeEmptyToDoException(String s) {
        super(s);
    }
}
