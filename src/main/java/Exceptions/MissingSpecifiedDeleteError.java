package Exceptions;

import Exceptions.DukeException;

public class MissingSpecifiedDeleteError extends DukeException {

    /**
     * Initializes MissingSpecifiedDeleteError
     * @param message
     */
    public MissingSpecifiedDeleteError(String message) {
        super(message);
    }

}
