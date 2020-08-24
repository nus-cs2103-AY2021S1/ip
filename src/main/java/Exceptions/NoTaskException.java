package Exceptions;

import Exceptions.DukeException;

public class NoTaskException extends DukeException {
    public NoTaskException(String message){
        super(message);
    }
}
