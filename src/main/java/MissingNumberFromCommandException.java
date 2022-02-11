/**
 * Class to hold the MissingNumberFromCommandException message of a MissingNumberFromCommandException.
 */
public class MissingNumberFromCommandException extends DukeException {

    /**
     * Custom message of a MissingNumberFromCommandException, reminding the user to type in a valid number with their
     * command.
     */
    @Override
    public String getMessage() {
        return Ui.LINE + "     OOPS!!! Please type in the done command followed by a valid task number. :-("
                + Ui.LINE;
    }
}
