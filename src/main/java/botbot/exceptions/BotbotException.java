package botbot.exceptions;

public class BotbotException extends Exception {
    public BotbotException(String e) {
        super("    oops! " + e + "\n");
    }
}
