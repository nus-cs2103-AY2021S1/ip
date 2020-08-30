/**
 * The exception that is thrown when the user command is invalid.
 */
public class NotTaskException extends DukeException {
    public NotTaskException() {
        super("I know you like to talk to me :D But I cannot understand what task this is..."
                + "Is it because my ears got bitten by a mouse? :(");
    }
}
