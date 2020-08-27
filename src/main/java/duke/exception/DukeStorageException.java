package duke.exception;

/**
 * An indication of file handling error and IO error.
 */
public class DukeStorageException extends DukeException{

    public DukeStorageException(String message) {
        super(message);
    }
}