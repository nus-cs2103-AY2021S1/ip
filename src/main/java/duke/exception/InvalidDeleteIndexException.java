package duke.exception;

public class InvalidDeleteIndexException extends DukeException {
    public InvalidDeleteIndexException(int tasksSize) {
        super(String.format("No such task :(\nYou have %d tasks.", tasksSize));
    }
}
