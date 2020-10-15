package duke.exception;

/** Represents exceptions when Duke is not able to parse commands.
 * These parse errors can be from the user input or when reading in the saved
 * task storage text file.
 */
public class DukeParseException extends DukeException {

    /**
     * Constructor method.
     *
     * @param message the error message <code>String</code>.
     */
    public DukeParseException(String message) {
        super(message);
    }
}
