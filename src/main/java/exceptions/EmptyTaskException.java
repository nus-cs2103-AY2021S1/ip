package main.java.exceptions;

import main.java.exceptions.DukeException;

public class EmptyTaskException extends DukeException {
    public EmptyTaskException() {
        this("Please specify task index. (´∀`)");
    }
    public EmptyTaskException(String errorMessage) {
        super(errorMessage);
    }
}
