/**
 * Represents a type of PandaBotException which is thrown when insufficient
 * number of arguments is passed into a method.
 */
public class PandaBotInsufficientArgumentException extends PandaBotException {

    /**
     * Creates a new PandaBotInsufficientArgumentException object which
     * is used to show that there is insufficient number of arguments passed
     * into a method.
     */
    public PandaBotInsufficientArgumentException() {
        super("OOPS! Insufficient arguments given. Make sure you give me all the details!");
    }
}
