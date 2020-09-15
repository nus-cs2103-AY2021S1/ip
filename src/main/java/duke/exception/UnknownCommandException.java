package duke.exception;

/**
 * Represents the exception when the user enters a command that the Duke chat bot
 * is unable to recognise.
 */
public class UnknownCommandException extends DukeException {
    public UnknownCommandException() {
        super("I don't know what that means ┐('～`;)┌");
    }
}
