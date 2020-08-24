package main.java.Exception;

import main.java.Exception.DukeException;

public class TodoException extends DukeException {

    @Override
    public String toString() {
        return super.toString() + " The description of a todo cannot be empty";
    }
}
