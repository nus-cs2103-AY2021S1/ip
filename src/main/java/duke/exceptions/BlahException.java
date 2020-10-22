package duke.exceptions;

/**
 * Exception class to represent exceptions thrown for 'blah' commands.
 */
public class BlahException extends DukeException {

    /**
     * Class constructor.
     */

    public BlahException() {
        super("You're talking gibberish. Cannot make sense of what you just said.");
    }
}
