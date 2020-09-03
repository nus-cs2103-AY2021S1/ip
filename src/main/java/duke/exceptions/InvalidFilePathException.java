package duke.exceptions;

import static duke.utils.Messages.MESSAGE_INVALID_FILE_PATH;

/**
 * Thrown to indicate that the file path is invalid.
 */
public class InvalidFilePathException extends DukeException {

    /**
     * Constructs an InvalidFilePathException with the relevant detail message.
     */
    public InvalidFilePathException() {
        super(MESSAGE_INVALID_FILE_PATH);
    }
}
