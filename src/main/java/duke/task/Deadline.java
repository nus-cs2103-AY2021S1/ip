package main.java.duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class Deadline extends Task {

    protected LocalDateTime localDateTime;
    protected String date;

    DateTimeFormatter fmt = new DateTimeFormatterBuilder()
            .appendPattern("[d-MM-yyyy HHmm][yyyy-MM-d HHmm]")
            .optionalStart()
            .appendPattern(" HHmm")
            .optionalEnd()
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();

    public Deadline(String description, String by) {
        super(description);
        this.date = by;
        by = by.replace('/', '-');
        this.localDateTime = LocalDateTime.parse(by, fmt);
    }

    public String getDateTime() {
        return this.localDateTime.format(DateTimeFormatter.ofPattern("d-MM-yyyy HHmm"));
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                localDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + ")";
    }
}
