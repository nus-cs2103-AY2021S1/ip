package duke.exception;

import duke.exception.DukeException;

public class InvalidTaskTypeException extends DukeException {
    public InvalidTaskTypeException() {
        super("Oops that wasn't a valid task type!");
    }
}
