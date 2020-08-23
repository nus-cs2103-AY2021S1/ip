/**
 * Class to handle cases when an invalid command is entered
 * @author vanGoghhh
 */

public class InvalidCommandException extends DukeException {

    public InvalidCommandException() {
        super("Sorry this is an unrecognised command");
    }
}
