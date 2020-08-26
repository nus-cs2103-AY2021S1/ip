package duke.exception;

import duke.exception.DukeException;

public class UnknownCommandException extends DukeException {
    public UnknownCommandException(String errorMessage) {
        super(errorMessage);
    }
}
