package main.java.exceptions;

import main.java.exceptions.DukeException;

public class EmptyTimeException extends DukeException {
    public EmptyTimeException() {
        this("Please specify task description. (´∀`)");
    }
    public EmptyTimeException(String errorMessage) {
        super(errorMessage);
    }
}
