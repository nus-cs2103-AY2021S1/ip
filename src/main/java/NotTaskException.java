/**
 * The exception that is thrown when the user command is invalid.
 */
public class NotTaskException extends DukeException{
    public NotTaskException() {
        super("Excuse me, Lao Duke is not so smart. You must follow the format for input... Don't anyhow hor >:(");
    }
}
