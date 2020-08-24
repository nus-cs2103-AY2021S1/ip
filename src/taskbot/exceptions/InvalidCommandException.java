package taskbot.exceptions;

/**
 * This exceptions handles when the user inputs an
 * incorrect command
 */
public class InvalidCommandException extends TaskbotException {
    public InvalidCommandException(String msg) {
        super(msg);
    }
}
