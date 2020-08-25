package Exceptions;

import Exceptions.DukeException;

public class NoTaskException extends DukeException {

    /**
     * Initializes NoTaskException
     * @param message
     */
    public NoTaskException(String message){
        super(message);
    }

}
