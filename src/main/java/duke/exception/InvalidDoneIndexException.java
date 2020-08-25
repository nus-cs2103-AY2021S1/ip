package duke.exception;

public class InvalidDoneIndexException extends DukeException {
    public InvalidDoneIndexException(int tasksSize) {
        super(String.format("No such task :(\nYou have %d tasks.", tasksSize));
    }
}
