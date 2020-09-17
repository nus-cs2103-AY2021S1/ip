package duke.exception;

import static duke.util.Keyword.INVALID_SIMPLE_COMMAND_ONE;
import static duke.util.Keyword.INVALID_SIMPLE_COMMAND_TWO;

import duke.command.SimpleCommandType;
import duke.ui.Ui;

/**
 * Thrown when the user inputs a wrong delete or done format.
 */
public class InvalidSimpleCommandException extends DukeException {

    /**
     * Initializes the InvalidSimpleCommandException object with the error message suggesting the proper format.
     *
     * @param simpleCommandType Type of simple command, either delete or done.
     */
    public InvalidSimpleCommandException(SimpleCommandType simpleCommandType) {
        super(getMessage(simpleCommandType));
    }

    /**
     * Obtains the error message.
     *
     * @param simpleCommandType Either Delete or Done command.
     * @return Formatted message.
     */
    private static String getMessage(SimpleCommandType simpleCommandType) {
        String errorMessage = String.format(INVALID_SIMPLE_COMMAND_ONE, simpleCommandType.toTitleCase());
        String suggestionMessage = String.format(INVALID_SIMPLE_COMMAND_TWO, simpleCommandType);
        return Ui.stringFormatter(errorMessage, suggestionMessage);
    }
}
