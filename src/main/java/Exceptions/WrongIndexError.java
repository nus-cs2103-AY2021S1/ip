package Exceptions;

import Exceptions.DukeException;

public class WrongIndexError extends DukeException {

    /**
     * Initializes WrongIndexError
     * @param message
     */
    public WrongIndexError (String message){
        super(message);
    }
}
