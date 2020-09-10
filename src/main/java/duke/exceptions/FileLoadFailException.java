package duke.exceptions;

/**
 * Represents exception thrown when unable to load file.
 */
public class FileLoadFailException extends DukeException {
    public FileLoadFailException() {
        super("There was a problem loading the file.");
    }
}
