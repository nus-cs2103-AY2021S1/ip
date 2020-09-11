package duke.exceptions;

/**
 * Thrown when user tries to retrieve an invalid index
 */
public class DukeInvalidIndexException extends DukeException{
    
    public DukeInvalidIndexException (String exceptionMessage) {
        super(exceptionMessage);
    }
    
}
