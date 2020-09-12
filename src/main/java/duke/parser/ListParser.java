package duke.parser;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.ListCommand;
import duke.exceptions.EmptyTaskException;
import duke.exceptions.EmptyTimeException;
import duke.exceptions.WrongDateFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ListParser extends Parser{
    public static Command parseListCommand(String[] parseArray) throws WrongDateFormatException {
        try {
            if (isOneWordCommand(parseArray)) {
                return new ListCommand(null);
            } else {
                String time = parseArray[1];
                LocalDate date = LocalDate.parse(time);
                return new ListCommand(date);
            }
        } catch(DateTimeParseException ex) {
            throw new WrongDateFormatException();
        }
    }
}
