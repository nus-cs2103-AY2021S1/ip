package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;


public class Deadline extends Task {
    protected String by;
    protected LocalDate date;


    public Deadline(String description, String by) {
        super(description);
        LocalDate localDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.date = localDate;
        this.by = localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
    }

    public static boolean checkDateFormat(String date) throws DukeException{
        int stringLength = date.length();
        if (stringLength != 10) {
            throw new DukeException("That is not a valid date!");
        } else {
            try {
                LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return true;
            } catch (DateTimeParseException e) {
                throw new DukeException("Please check your date! It is clearly not realistic >:(");
            }
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
