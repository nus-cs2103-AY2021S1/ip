public class InvalidFormatException extends BotbotException {
    InvalidFormatException(String e) {
        super("invalid format! please follow '" + e + "'!");
    }
}
