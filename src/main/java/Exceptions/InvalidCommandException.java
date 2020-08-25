package Exceptions;

import Exceptions.DukeException;

public class InvalidCommandException extends DukeException {

    /**
     * Initializes InvalidCommandException
     * @param message
     */
    public InvalidCommandException(String message){
        super(message);
    }

}
