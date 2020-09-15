package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.commands.Command;
import duke.commands.ListCommand;
import duke.exceptions.WrongDateFormatException;

public class ListParser extends Parser {
    /**
     * Lists all task/tasks on certain day if date if provided.
     *
     * @param parseArray An array of info: ["list", date(optional)].
     * @return A listCommand.
     * @throws WrongDateFormatException If the date is not in the format of yyyy-mm-dd.
     */
    public static Command parseListCommand(String[] parseArray) throws WrongDateFormatException {
        try {
            if (isOneWordCommand(parseArray)) {
                return new ListCommand(null);
            } else {
                String time = parseArray[1];
                LocalDate date = LocalDate.parse(time);
                return new ListCommand(date);
            }
        } catch (DateTimeParseException ex) {
            throw new WrongDateFormatException();
        }
    }
}
