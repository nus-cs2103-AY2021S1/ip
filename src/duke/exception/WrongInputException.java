package duke.exception;

import duke.exception.DukeException;

public class WrongInputException extends DukeException {

    /**
     * Constructs the exception for invalid input or command.
     */
    public WrongInputException() {
        super("Please enter a valid command.");
    }
}
