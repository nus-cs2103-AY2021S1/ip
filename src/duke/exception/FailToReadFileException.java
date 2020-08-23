package duke.exception;

public class FailToReadFileException extends DukeException {
    public FailToReadFileException() {
        super("Something went wrong when reading the storage file! Please try again.");
    }
}
