package Exceptions;

import Exceptions.DukeException;

public class WrongDeleteIndexError extends DukeException {
    public WrongDeleteIndexError (String message){
        super(message);
    }
}
