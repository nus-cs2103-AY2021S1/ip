package exceptions;

/**
 * An exception results from invalid syntax
 */
public class InvalidCommandException extends DukeException {
    public InvalidCommandException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Oh no:(, your command is not valid with message: " + super.message;
    }
}
