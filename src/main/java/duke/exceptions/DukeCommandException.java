package duke.exceptions;

/**
 * Error Type of a Invalid Command in Duke Application.
 */
public class DukeCommandException extends DukeException {
    /**
     * Constructor class for DukeCommandException
     * @param badCommand the command that is unrecognisable by Duke Application
     */
    public DukeCommandException(String badCommand) {
        super(badCommand , 1);
    }
}
