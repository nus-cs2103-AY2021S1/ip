package Exceptions;

import Exceptions.DukeException;

public class WrongIndexError extends DukeException {
    public WrongIndexError (String message){
        super(message);
    }
}
