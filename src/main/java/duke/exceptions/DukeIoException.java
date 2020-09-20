package duke.exceptions;

/**
 * Error type for I/O that appear when trying to perform a read or write command.
 */
public class DukeIoException extends DukeException {
    /**
     * Constructor for I/O exception class.
     * @param badCommand the part of the I/O process that is causing an error
     */
    public DukeIoException(String badCommand) {
        super(badCommand, 0);
    }
}
