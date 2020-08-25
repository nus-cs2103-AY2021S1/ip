package exceptions;

/**
 * Exception Class to encapsulate any exceptional error that does not fit any of the previous criterion
 */
public class DukeUnknownException extends DukeException{
    public DukeUnknownException(String m){
        super(m,99);
    }
}
