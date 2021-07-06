package duke.exception;

/**
 * Represents the exception that the task with the same description has been created.
 */
public class TaskExistException extends DukeException {

    public TaskExistException() {
        super("You task has been in the list. Please change the description.");
    }

}
