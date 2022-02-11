/**
 * Class to hold the MissingDescriptionException message of a MissingDescriptionException.
 */
public class MissingDescriptionException extends DukeException {

    /**
     * Custom message of a MissingDescriptionException, reminding the user to type in a valid description with their
     * command.
     */
    @Override
    public String getMessage() {
        return Ui.LINE + "     OOPS!!! Your command needs to have a description. :-(" + Ui.LINE;
    }
}
