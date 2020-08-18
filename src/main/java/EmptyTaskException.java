public class EmptyTaskException extends BotbotException {
    EmptyTaskException(String e) {
        super("the description of " + e + " cannot be empty!");
    }
}
