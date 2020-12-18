package duke.exception;

// Signals an error in the save file.
public class InvalidSaveFileException extends DukeException {
    public InvalidSaveFileException(String errMsg) {
        super(errMsg);
    }
}
