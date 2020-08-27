/**
 * Represents a type of PandaBotException which is thrown when the user passes in
 * an invalid command.
 */
public class PandaBotInvalidCommandException extends PandaBotException {

    /**
     * Creates a new PandaBotInvalidCommandException object which
     * is used to show that the command passed cannot be recognised and is invalid.
     */
    public PandaBotInvalidCommandException() {
        super("OOPS! I'm sorry, but I don't know what that means :c");
    }
}
