package main.exception;

/**
 * Thrown to indicate the description of a task is empty.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.2
 * @since v0.1
 */
public class EmptyMessageException extends StuffException {

    /**
     * Constructs an EmptyMessageException instance with the
     * messaged tailored for the command.
     * @param command the command that does not have a description.
     */
    public EmptyMessageException(String command) {
        super(String.format("The description of a %s cannot be empty.", command));
    }
}
