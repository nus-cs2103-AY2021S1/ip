package main.java.exceptions;

import main.java.exceptions.DukeException;

public class WrongDateFormatException extends DukeException {
    public WrongDateFormatException(String errorMessage) {
        super(errorMessage);
    }

    public WrongDateFormatException(){
        this("java.tasks.Task not saved due to wrong format. ꉂ `o´ ) Please specify the date in the following format: \n" +
                "yyyy-mm-dd i.e. 2020-01-01");
    }
}
