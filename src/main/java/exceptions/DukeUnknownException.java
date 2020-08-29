package exceptions;

/**
 * Exception Class to encapsulate any exceptional error that does not fit any of the previous criterion
 */
public class DukeUnknownException extends DukeException{
    /**
     * Constructor class for the DukeUnknownException
     * @param m message or input that caused this unexpected error.
     */
    public DukeUnknownException(String m){
        super(m,99);
    }
}
