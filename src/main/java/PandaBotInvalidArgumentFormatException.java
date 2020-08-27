/**
 * Represents a type of PandaBotException which is thrown when an an invalid input
 * is passed as an argument into a method.
 */
public class PandaBotInvalidArgumentFormatException extends PandaBotException {

    /**
     * Creates a new PandaBotInvalidArgumentFormatException object which
     * is used to show that an invalid input is passed as an argument
     * into a method.
     */
    public PandaBotInvalidArgumentFormatException(String msg) {
        super("Invalid format given: " + msg);
    }
}
