package main.exception;

/**
 * Thrown to indicate the event format is invalid.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.2
 * @since v0.1
 */
public class InvalidEventFormatException extends StuffException {

    /**
     * Constructs an InvalidEventFormatException instance.
     */
    public InvalidEventFormatException() {
        super("An event needs to have this format:\n"
                + "\"task name\" /at \"event time\"");
    }
}
