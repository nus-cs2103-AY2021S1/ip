package duke.exception;

/**
 * Exception thrown when a done command uses an invalid index.
 */
public class InvalidDoneIndexException extends DukeException {
    /**
     * Constructs an InvalidDoneIndexException.
     *
     * @param tasksSize The number of tasks in the tasklist.
     */
    public InvalidDoneIndexException(int tasksSize) {
        super(String.format("No such task :(\nYou have %d tasks.", tasksSize));
    }
}
