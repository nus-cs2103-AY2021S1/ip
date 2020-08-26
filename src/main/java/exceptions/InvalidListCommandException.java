package exceptions;

public class InvalidListCommandException extends DukeException{
    public InvalidListCommandException() {
        super("list command is invalid! Input \"list\" to show task in the task list.");
    }
}
