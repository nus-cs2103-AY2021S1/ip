package duke.exception;

/** An exception when the command given is not a valid command:
 *     list, done, delete, bye, deadline, event, or todo. */
public class UnrecognizedTaskException extends DukeException {

    /** Constructs an UnrecognizedTaskException. */
    public UnrecognizedTaskException() {
        super("OOPS. Bolot does not recognize the command :( Try again.");
    }
}