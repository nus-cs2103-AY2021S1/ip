package duke.exception;

import duke.command.SimpleCommandType;
import duke.ui.Ui;

/**
 * Thrown when the user inputs a wrong delete or done format.
 */
public class InvalidSimpleCommandException extends DukeException {

    private static final String LINE_1 = "%s format is invalid.";
    private static final String LINE_2 = "Please try again with a proper format like '%s 3'";

    /**
     * Initializes the InvalidSimpleCommandException object with the error message suggesting the proper format.
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
        String errorMessage = String.format(LINE_1, simpleCommandType.toTitleCase());
        String suggestionMessage = String.format(LINE_2, simpleCommandType);
        return Ui.stringFormatter(errorMessage, suggestionMessage);
    }
}
