public class BotbotException extends Exception {
    BotbotException(String e) {
        super("    oops! " + e + "\n");
    }
}
