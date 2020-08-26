package Parser;

import Command.FilterDateCommand;
import Errors.ErrorExceptions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParseFilter extends Parse{
    public static void execute(String date) throws ErrorExceptions {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d-MM-uuuu");
        LocalDate d = LocalDate.parse(date,format);
        FilterDateCommand.execute(d);
    }
}
