/**
 * Class to hold the MissingInfoException message of a MissingInfoException.
 */
public class MissingInfoException extends DukeException {

    /**
     * Custom message of a MissingInfoException, reminding the user to type in a valid description with their
     * command.
     */
    @Override
    public String getMessage() {
        return "     OOPS!!! Your command needs to be followed by a description. :-(";
    }
}
