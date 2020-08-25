package main.exception;

/**
 * Thrown to indicate any exceptions in the duke application.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.1
 * @since v0.1
 */
public class DukeException extends Exception {

    /**
     * Constructs an DukeException instance with a message.
     * @param message the message for the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
