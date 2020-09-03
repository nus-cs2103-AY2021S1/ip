package main.exception;

/**
 * Thrown to indicate input command is unknown.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.2
 * @since v0.1
 */
public class UnknownCommandException extends StuffException {

    /**
     * Constructs an UnknownCommandException instance.
     */
    public UnknownCommandException() {
        super("I'm sorry, but I don't know what that means.");
    }
}
