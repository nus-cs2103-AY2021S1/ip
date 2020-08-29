package botbot.exceptions;

public class EmptyTaskNumberException extends BotbotException {
    public EmptyTaskNumberException() {
        super("the task number to be marked as done cannot be empty!");
    }
}
