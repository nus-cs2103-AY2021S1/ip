public class EmptyTaskNumberException extends BotbotException {
    EmptyTaskNumberException() {
        super("the task number to be marked as done cannot be empty!");
    }
}
