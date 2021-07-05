package luke.exception;

/**
 * Represents an exception thrown when no details are given for a command.
 */
public class LukeEmptyCommandException extends LukeException {
    /**
     * Creates a LukeEmptyCommandException object that indicates no command details.
     *
     * @param command command given by the user
     */
    public LukeEmptyCommandException(String command) {
        super(String.format("Sorry, '%s' command should not be empty. Please add details about the command.", command));
    }
}
