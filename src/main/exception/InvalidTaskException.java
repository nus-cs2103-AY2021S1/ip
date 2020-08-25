package main.exception;

/**
 * Thrown to indicate the task selected is invalid.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.1
 * @since v0.1
 */
public class InvalidTaskException extends DukeException {

    /**
     * Constructs an InvalidTaskException instance.
     */
    public InvalidTaskException() {
        super("     ☹ OOPS!!! Your selected task does not exist!");
    }
}
