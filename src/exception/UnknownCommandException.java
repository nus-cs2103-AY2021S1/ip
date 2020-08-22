package exception;

/**
 * Represents an exceptions stating the command is not defined.
 */
public class UnknownCommandException extends DukeException {
    @Override
    public String toString() {
        return super.toString() + " " + "I'm sorry, but I don't know what that means :-(";
    }
}
