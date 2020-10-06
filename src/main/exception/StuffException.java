package main.exception;

/**
 * Thrown to indicate any exceptions in the stuff application.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.2
 * @since v0.1
 */
public class StuffException extends Exception {

    /**
     * Constructs an StuffException instance with a message.
     * @param message the message for the exception.
     */
    public StuffException(String message) {
        super(message);
    }
}
