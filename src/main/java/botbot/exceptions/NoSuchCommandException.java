package botbot.exceptions;

public class NoSuchCommandException extends BotbotException {
    public NoSuchCommandException() {
        super("sorry, I don't know what that means!");
    }
}
