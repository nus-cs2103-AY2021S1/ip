package main.exception;

/**
 * Thrown to indicate the deadline format is invalid.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.2
 * @since v0.1
 */
public class InvalidDeadlineFormatException extends StuffException {

    /**
     * Constructs an InvalidDeadlineFormatException instance.
     */
    public InvalidDeadlineFormatException() {
        super("A deadline needs to have this format:\n"
                + "\"task name\" /by \"task deadline\"");
    }
}
