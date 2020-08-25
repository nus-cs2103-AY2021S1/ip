package Exceptions;

public class InvalidByeCommandException extends DukeException{
    public InvalidByeCommandException() {
        super("bye command is invalid! Input \"bye\" to exit");
    }
}
