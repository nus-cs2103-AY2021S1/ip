package duke.exception;

/** An exception that occurs when there's an error when loading a file. */
public class FileLoadError extends DukeException {

    /** Constucts a @FileLoadError exception. */
    public FileLoadError() {
        super("Error in reading file. Bolot will now create a new file.");
    }
}
