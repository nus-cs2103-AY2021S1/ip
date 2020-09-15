package duke;

public class DukeInvalidDateException extends DukeException {

    /**
     * Class constructor
     * @param command String of the command executed.
     */
    public DukeInvalidDateException(String command) {
        super("Please enter a valid date for "
                + command + "!\nDate and time format"
                + " should be 'yyyy-mm-dd'"
                + "\n(eg. 2019-01-05)");
    }
}
