package duke.exception;

import static duke.util.Keyword.FILE_FORMAT_ERR;

import duke.ui.Ui;

/**
 * Thrown when the file format of the CSV is not properly formatted.
 */
public class InvalidFileFormatException extends DukeException {

    /**
     * Initializes the InvalidFileFormatException.
     */
    public InvalidFileFormatException() {
        super(Ui.stringFormatter(FILE_FORMAT_ERR));
    }
}
