/**
 * Thrown to indicate that the user has put in an invalid duke command.
 */
public class DukeTimeParseException extends DukeException {

    /**
     * Constructs a DukeException with a relevant detail message.
     *
     * @param s Represents the error message.
     */
    public DukeTimeParseException(String s) {
        super("OOPS!!! The invalid date format has "
                + "been keyed in. PLease enter in dd-MM-yyyy HH:mm format");
    }
}
