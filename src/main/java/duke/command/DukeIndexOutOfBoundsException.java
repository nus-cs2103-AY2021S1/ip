package duke.command;

/**
 * The class DukeIndexOutOfBoundsException denotes a Duke IndexOutOfBoundsException.
 *
 * @author Alvin Chee
 */
public class DukeIndexOutOfBoundsException extends DukeRunTimeException {
    /**
     * Constructs a DukeIndexOutOfBoundsException
     *
     * @param errorMessage  Error message of the exception.
     */
    public DukeIndexOutOfBoundsException(String errorMessage) {
        super(errorMessage);
    }
}
