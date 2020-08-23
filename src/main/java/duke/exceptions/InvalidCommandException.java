package duke.exceptions;

public class InvalidCommandException extends DukeException {

    @Override
    public String getMessage() {
        return "Your command cannot be processed. Please key in a valid command.";
    }
}

