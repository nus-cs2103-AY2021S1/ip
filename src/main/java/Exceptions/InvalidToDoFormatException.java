package Exceptions;

public class InvalidToDoFormatException extends DukeException{
    public InvalidToDoFormatException() {
        super("☹ OOPS!!! The description of a todo cannot be empty.");
    }
}
