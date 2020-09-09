/**
 * Represents exception thrown when unable to load file.
 */
public class FileLoadFailException extends DukeException {
    FileLoadFailException() {
        super("There was a problem loading the file.");
    }
}
