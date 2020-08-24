package duke.exceptions;

/**
 * Exception to represent exceptions thrown for invalid dates in tasks.
 */
public class DukeInvalidTimeException extends DukeException {

    /**
     * Class constructor.
     */

    public DukeInvalidTimeException() {
        super("Your command has to be in a proper format like " +
                "the one here \n\t <command> <activity> <by/at> <Year-Month-Day Hour:Minute>.");
    }
}
