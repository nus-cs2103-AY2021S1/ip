package main.exception;

/**
 * Thrown to indicate input command is unknown.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.1
 * @since v0.1
 */
public class UnknownCommandException extends DukeException {

    /**
     * Constructs an UnknownCommandException instance.
     */
    public UnknownCommandException() {
        super("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
