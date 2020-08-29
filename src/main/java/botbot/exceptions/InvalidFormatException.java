package botbot.exceptions;

public class InvalidFormatException extends BotbotException {
    public InvalidFormatException(String e) {
        super("invalid format! please follow '" + e + "'!");
    }
}
