package duke.task;

import duke.command.DukeRunTimeException;

/**
 * The class DukeNumberFormatException denotes a Duke DateTimeParseException.
 *
 * @author Alvin Chee
 */
public class DukeNumberFormatException extends DukeRunTimeException {
    /**
     * Constructs a DukeNumberFormatException
     *
     * @param errorMessage  Error message of the exception.
     */
    public DukeNumberFormatException(String errorMessage) {
        super(errorMessage);
    }
}
