package Exceptions;

public class InvalidDeleteFormatException extends DukeException{
    public InvalidDeleteFormatException() {
        super("☹ OOPS!!! Format for delete command doesnt seem to be correct. Eg. delete 2");
    }
}
