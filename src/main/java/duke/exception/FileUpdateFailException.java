package duke.exception;

import static duke.util.Keyword.FILE_UPDATE_FAIL_MESSAGE;

import duke.ui.Ui;

/**
 * Thrown when the csv file fails to update properly.
 */
public class FileUpdateFailException extends DukeException {

    /**
     * Initializes the FileUpdateFailException.
     */
    public FileUpdateFailException() {
        super(Ui.stringFormatter(FILE_UPDATE_FAIL_MESSAGE));
    }
}
