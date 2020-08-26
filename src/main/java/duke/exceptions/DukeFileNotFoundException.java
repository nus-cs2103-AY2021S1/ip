package duke.exceptions;

/**
 * A subclass of DukeException which handles the case
 * when the text file that stores all the task is not found or corrupted.
 */
public class DukeFileNotFoundException extends DukeException {
    private static String ERROR_FILE_NOT_FOUND = "⚠⚠⚠ File is not found. Rerun program";

    /**
     * DukeFileNotFoundException constructor.
     */
    public DukeFileNotFoundException() {
        super(ERROR_FILE_NOT_FOUND);
    }
}

