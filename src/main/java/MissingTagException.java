/**
 * Class to hold the MissingTagException message of a MissingTagException.
 */
public class MissingTagException extends DukeException {

    /**
     * Custom message of a MissingTagException, reminding the user to type in a valid tag with their event/deadline
     * command.
     */
    @Override
    public String getMessage() {
        return Ui.LINE + "     OOPS!!! Your command needs to be followed by a valid tag. :-(\n"
                + "     For the event command, this tag is /at\n"
                + "     For the deadline command, this tag is /by" + Ui.LINE;
    }
}
