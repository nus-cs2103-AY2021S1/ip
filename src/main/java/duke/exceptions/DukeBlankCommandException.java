package duke.exceptions;

/**
 * Error Type of a Blank Command in Duke Application.
 */
public class DukeBlankCommandException extends DukeException {
    /**
     * Constructor for DukeBlankCommandException for a blank command
     * @param s String form of the bad input
     */
    public DukeBlankCommandException(String s) {
        super(s, 4);
    }
}
