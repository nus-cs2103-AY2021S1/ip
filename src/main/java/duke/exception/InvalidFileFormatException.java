package duke.exception;

import duke.ui.Ui;

/**
 * Thrown when the file format of the CSV is not properly formatted.
 */
public class InvalidFileFormatException extends DukeException {

    private static final String INVALID_FILE_FORMAT = "CSV file is poorly formatted!";

    /**
     * Initializes the InvalidFileFormatException.
     */
    public InvalidFileFormatException() {
        super(Ui.stringFormatter(INVALID_FILE_FORMAT));
    }
}
