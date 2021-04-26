package duke.exceptions;

/**
 * A subclass of DukeException which handles the case
 * when the user input does not match any commands.
 */
public class DukeInvalidTaskException extends DukeException {

    public static final String ERROR_INVALID_TASK = "Paw-don me, "
            + "I cannot understand you ^._.^";


    /**
     * DukeInvalidTaskException constructor.
     */
    public DukeInvalidTaskException() {
        super(ERROR_INVALID_TASK);
    }
}
