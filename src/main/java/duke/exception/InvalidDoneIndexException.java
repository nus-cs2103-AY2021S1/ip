package duke.exception;

/**
 * Exception thrown when a done command uses an invalid index.
 */
public class InvalidDoneIndexException extends DukeException {
    public InvalidDoneIndexException(int tasksSize) {
        super(String.format("No such task :(\nYou have %d tasks.", tasksSize));
    }
}
