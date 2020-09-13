package duke.exceptions;

/**
 * Exception thrown when tasks in the data file do not match the desired format
 */
public class DukeInvalidStorageException extends DukeException{
    
    public DukeInvalidStorageException (String exceptionMessage) {
        super (exceptionMessage);
    }
}
