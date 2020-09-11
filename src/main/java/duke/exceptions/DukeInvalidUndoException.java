package duke.exceptions;

/**
 * Thrown when user tries to undo a non existent command
 */
public class DukeInvalidUndoException extends DukeException {

    public DukeInvalidUndoException (String exceptionMessage) {
        super (exceptionMessage);
    }
    
}
