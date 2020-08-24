package duke.exception;

import duke.exception.DukeException;

public class TodoException extends DukeException {

    public TodoException() {
        super("Please specify what do you want to do~");
    }

}
