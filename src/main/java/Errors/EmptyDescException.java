package Errors;

import Errors.DukeException;

public class EmptyDescException extends DukeException {
    public EmptyDescException() {
        super("☹ OOPS!!! The description cannot be empty");
    }
}