package Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Command.FilterDateCommand;
import Errors.ErrorExceptions;



/**
 * Represents the bridge that calls the appropriate filter by date command.
 */
public class ParseFilter extends Parse {

    /**
     * Calls the filter command.
     *
     * @param date date to filter.
     * @throws ErrorExceptions wrong date format.
     */
    public static void execute(String date) throws ErrorExceptions {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d-MM-uuuu");
        LocalDate d = LocalDate.parse(date, format);
        FilterDateCommand.execute(d);
    }
}
