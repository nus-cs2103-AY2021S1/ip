package main.java.exceptions;

import main.java.exceptions.DukeException;

public class PathNoFoundException extends DukeException {
    public PathNoFoundException(String errorMessage) {
        super(errorMessage);
    }
}
