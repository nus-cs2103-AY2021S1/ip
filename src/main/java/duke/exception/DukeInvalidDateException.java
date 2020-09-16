package duke.exception;

/**
 * Represents exception to signify an invalid task date.
 */
public class DukeInvalidDateException extends DukeException {

    /**
     * Class constructor
     * @param command String of the command executed.
     */
    public DukeInvalidDateException(String command) {
        super("Please enter a valid date for "
                + command + "!\nDate and time format"
                + " should be 'dd/mm/yyyy 24h-time'"
                + "\n(eg. 01/01/2020 1900)");
    }
}
