package duke.exceptions;

public class DukeFileNotFoundException extends DukeException {
    private static String ERROR_FILE_NOT_FOUND = "⚠⚠⚠ File is not found. Rerun program";
    public DukeFileNotFoundException() {
        super(ERROR_FILE_NOT_FOUND);
    }
}

