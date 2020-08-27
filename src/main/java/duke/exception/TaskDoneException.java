package duke.exception;

/**
 * Represents a Invalid Instruction Format DukeException in the program.
 * It is to feedback to the user about marking an already completed DukeTask.
 */
public class TaskDoneException extends DukeException {
    public TaskDoneException() {
        super("The task is already completed!", TaskDoneException.class.getName());
    }
}
