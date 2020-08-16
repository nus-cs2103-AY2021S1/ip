/**
 * This class handles the case where the command exists, but
 * wrong format is given
 */
public class WrongFormatException extends Exception {
    public WrongFormatException(String msg) {
        super(msg);
    }
}
