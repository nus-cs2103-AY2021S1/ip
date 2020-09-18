package duke.task;

import duke.command.DukeRunTimeException;

/**
 * The class DukeDateTimeParseException denotes a Duke DateTimeParseException.
 *
 * @author Alvin Chee
 */
public class DukeDateTimeParseException extends DukeRunTimeException {
    /**
     * Constructs a DukeDateTimeParseException
     *
     * @param errorMessage  Error message of the exception.
     */
    public DukeDateTimeParseException(String errorMessage) {
        super(errorMessage);
    }
}
