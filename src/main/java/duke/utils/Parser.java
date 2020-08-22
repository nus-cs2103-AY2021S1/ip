package duke.utils;

import duke.DukeException;
import duke.Messenger;
import duke.TaskType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        if (command.equals("")) {
            throw new DukeException(Messenger.EMPTY_COMMAND_ERROR);
        }

        String[] split = command.split(" ", 2);
        int len = split.length;

        String status = split[0];
        if (TaskType.valueOfStatus(status) == null && !status.equals("done") && !status.equals("delete") && !status.equals("find")) {
            throw new DukeException(Messenger.SPELL_ERROR);
        }

        // no description supplied
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
            if (split[0].charAt(0) == '/') {
                throw new DukeException(Messenger.EMPTY_CONTENT_ERROR);
            } else {
                throw new DukeException(Messenger.EMPTY_TIME_ERROR);
            }
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
    }
}
