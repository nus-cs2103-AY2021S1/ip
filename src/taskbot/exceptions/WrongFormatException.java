package taskbot.exceptions;

/**
 * This class handles the case where the command exists, but
 * wrong format is given
 */
public class WrongFormatException extends TaskbotException {
    public WrongFormatException(String msg) {
        super(msg);
    }
}
