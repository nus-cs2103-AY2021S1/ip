package mattbot.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import mattbot.Command.FilterDateCommand;
import mattbot.Errors.ErrorExceptions;



/**
 * Represents the bridge that calls the appropriate filter by date command.
 */
public class ParseFilter extends Parse {

    /**
     * Calls the filter command.
     * Returns the filtered list.
     *
     * @param date date to filter.
     * @return String filtered list.
     * @throws ErrorExceptions wrong date format.
     */

    public static String execute2(String date) throws ErrorExceptions {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d-MM-uuuu");
        LocalDate d = LocalDate.parse(date, format);
        return FilterDateCommand.execute2(d);
    }
}
