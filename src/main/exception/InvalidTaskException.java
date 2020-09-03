package main.exception;

/**
 * Thrown to indicate the task selected is invalid.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.2
 * @since v0.1
 */
public class InvalidTaskException extends StuffException {

    /**
     * Constructs an InvalidTaskException instance.
     */
    public InvalidTaskException() {
        super("Your selected task does not exist!");
    }
}
