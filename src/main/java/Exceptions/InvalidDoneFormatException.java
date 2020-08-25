package Exceptions;

public class InvalidDoneFormatException extends DukeException{
    public InvalidDoneFormatException() {
        super("☹ OOPS!!! Format for done command doesnt seem to be correct. Eg. done 2");
    }
}

