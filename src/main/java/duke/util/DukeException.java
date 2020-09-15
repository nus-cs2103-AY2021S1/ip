package duke.util;

/**
 * Throws a duke.Duke exception with a customised error message.
 */
public class DukeException extends Exception {

    /**
     * Constructor.
     * @param errorMessage string containing error message.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
        }
        }
