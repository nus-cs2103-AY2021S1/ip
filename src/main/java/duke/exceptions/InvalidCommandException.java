package duke.exceptions;

/**
 * Encapsulates the exception where the user keys in an invalid command.
 */

public class InvalidCommandException extends DukeException {

    @Override
    public String getMessage() {
        return "Your command cannot be processed. Please key in a valid command.";
    }
}

