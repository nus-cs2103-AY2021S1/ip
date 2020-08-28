package duke.exception;

/**
 * Exception thrown when a delete command uses an invalid index.
 */
public class InvalidDeleteIndexException extends DukeException {
    /**
     * Constructs an InvalidDeleteIndexException.
     *
     * @param tasksSize The number of tasks in the tasklist.
     */
    public InvalidDeleteIndexException(int tasksSize) {
        super(String.format("No such task :(\nYou have %d tasks.", tasksSize));
    }
}
