package luke.exception;

public class LukeEmptyCommandException extends LukeException {
    public LukeEmptyCommandException(String command) {
        super(String.format("Sorry, '%s' command should not be empty. Please add details about the command.", command));
    }
}
