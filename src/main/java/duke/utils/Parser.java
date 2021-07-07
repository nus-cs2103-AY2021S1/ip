package duke.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.DukeException;
import duke.types.ActionType;
import duke.types.TaskType;
import duke.ui.Messenger;



/**
 * Encapsulate a class that represent a parser to parse the command being passed to
 * the program.
 */
public class Parser {
    /**
     * Extracts a line of command into the action and the main body.
     *
     * @param command the line of command.
     * @return an array of string [action, main body].
     * @throws DukeException throws a duke exception on empty command or spell error.
     */
    public String[] extractAction(String command) throws DukeException {
        // command is empty
        if (command.matches(" *")) {
            throw new DukeException(Messenger.EMPTY_COMMAND_ERROR);
        }

        String[] split = command.split(" ", 2);

        String type = split[0];
        // command type does not match known command types
        if (TaskType.valueOfType(type) == null && ActionType.valueOfType(type) == null) {
            throw new DukeException(Messenger.SPELL_ERROR);
        }

        // no description supplied
        int len = split.length;
        if (len == 1) {
            throw new DukeException(Messenger.emptyDescriptionError(command));
        }
        return split;
    }

    /**
     * Extracts the date from the main body.
     *
     * @param command the main body of the line of command.
     * @return an array of String [content, date].
     * @throws DukeException throws a duke exception on empty content or time supplied.
     */
    public String[] extractDate(String command) throws DukeException {
        String[] split = command.split(" /by | /at ", 2);

        // validate if there are two parts
        if (split.length < 2) {
            String errorMessage = (split[0].charAt(0) == '/')
                    ? Messenger.EMPTY_CONTENT_ERROR
                    : Messenger.EMPTY_TIME_ERROR;
            throw new DukeException(errorMessage);
        }

        // validate if time is of the correct format
        String time = split[1];
        validateFormat(time);
        return split;
    }

    private void validateFormat(String value) throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate ld = LocalDate.parse(value, formatter);
            String result = ld.format(formatter);
            if (!result.equals(value)) {
                throw new DukeException(Messenger.DATE_FORMAT_ERROR);
            }
        } catch (DateTimeParseException exp) {
            throw new DukeException(Messenger.DATE_FORMAT_ERROR);
        }
        assert value.split("-").length == 3 : "date has the wrong format";
    }
}
