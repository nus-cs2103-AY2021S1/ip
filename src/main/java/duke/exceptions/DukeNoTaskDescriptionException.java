package duke.exceptions;

/**
 * Exception thrown when the description of a task is not specified by the user
 */
public class DukeNoTaskDescriptionException extends DukeException{
    
    public DukeNoTaskDescriptionException (String exceptionMessage) {
        super(exceptionMessage);
    }
}
