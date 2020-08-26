package duke.exception;

import duke.exception.DukeException;

public class DateParseException extends DukeException {
    public DateParseException(String errorMessage) {
        super(errorMessage);
    }
}
