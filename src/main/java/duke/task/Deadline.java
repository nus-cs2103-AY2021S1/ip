package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Deadline extends Task {
    protected String by;
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
