package luke.exception;

/**
 * Represents an exception thrown when invalid command is given.
 */
public class LukeUnknownCommandException extends LukeException {
    /**
     * Creates a LukeUnknwonCommandException object that indicates invalid commands.
     *
     * @param command command given by the user
     */
    public LukeUnknownCommandException(String command) {
        super(String.format("Sorry, '%s' is an unknown command. Please type in a valid command.", command));
    }
}
