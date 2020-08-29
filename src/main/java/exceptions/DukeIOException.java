package exceptions;

/**
 * Error type for I/O that appear when trying to perform a read or write command.
 */
public class DukeIOException extends DukeException{
    /**
     * Constructor for I/O exception class.
     * @param bad_cmd the part of the I/O process that is causing an error
     */
    public DukeIOException(String bad_cmd){
        super(bad_cmd, 0);
    }
}
