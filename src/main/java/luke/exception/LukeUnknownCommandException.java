package luke.exception;

public class LukeUnknownCommandException extends LukeException {
    public LukeUnknownCommandException(String command) {
        super(String.format("Sorry, '%s' is an invalid command. Please type in a valid command.", command));
    }
}
