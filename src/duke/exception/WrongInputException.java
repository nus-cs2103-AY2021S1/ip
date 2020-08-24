package duke.exception;

import duke.exception.DukeException;

public class WrongInputException extends DukeException {

    public WrongInputException() {
        super("Please enter a valid command.");
    }
}
