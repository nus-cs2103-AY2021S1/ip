package botbot.exceptions;

public class EmptyTaskException extends BotbotException {
    public EmptyTaskException(String e) {
        super("the description of " + e + " cannot be empty!");
    }
}
