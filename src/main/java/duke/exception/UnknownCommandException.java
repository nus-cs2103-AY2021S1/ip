package duke.exception;

import duke.exception.DukeException;

public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("Sorry I didn't understand that :(\n"
                + "How about entering 'help' instead?");
    }
}
