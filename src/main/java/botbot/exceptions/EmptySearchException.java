package botbot.exceptions;

public class EmptySearchException extends BotbotException {
    public EmptySearchException() {
        super("the search keyword cannot be empty!");
    }
}
