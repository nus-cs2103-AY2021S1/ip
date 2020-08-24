package duke.exceptions;

/** Thrown to indicate that there is a problem saving the file. */
public class StorageException extends DukeException {

    /** Constructs a StorageException with the relevant detail message. */
    public StorageException() {
        super("OOPS! Error saving file.");
    }
}
