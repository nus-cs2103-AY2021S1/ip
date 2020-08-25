/**
 * Thrown to indicate that the user has put in an empty time for an event.
 */
public class DukeEmptyEventTimeException extends DukeException {

    /**
     * Constructs a DukeEmptyEventTimeException with a relevant detail message.
     *
     * @param s Represents the error message.
     */
    DukeEmptyEventTimeException(String s) {
        super(s);
    }
}

