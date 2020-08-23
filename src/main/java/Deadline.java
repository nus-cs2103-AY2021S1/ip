import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected String by;
    protected LocalDateTime dateTime;
    public Deadline(String desc, String by) {
        super(desc, "deadline");
        this.by = by;
        parseDate(by);
    }
    public void parseDate(String input) {
        try {
            // changes 2020-10-10 1800 --> 10 Oct 2020 6:00 PM
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime parsedDateTime = LocalDateTime.parse(input, formatter);
            this.dateTime = parsedDateTime;
            this.by = parsedDateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a"));
        } catch (DateTimeParseException e) {
            // if time doesnt match datetime format then skip
            System.out.print("");
        }
    }

    @Override
    public String toSaveFormat() {
        return "deadline " + super.toSaveFormat() + " /by " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
