package Exceptions;

import Exceptions.DukeException;

public class MissingSpecifiedDeleteError extends DukeException {
    public MissingSpecifiedDeleteError(String message) {
        super(message);
    }
}
