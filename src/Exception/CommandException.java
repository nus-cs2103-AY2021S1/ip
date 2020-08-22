package Exception;

/**
 * Represents an exception which is thrown if the command is not known.
 */
public class CommandException extends DukeException {
    @Override
    public String toString() {
        String s = "OOPS!!! I don't know what that commands mean.\n";
        return s;
    }
}
