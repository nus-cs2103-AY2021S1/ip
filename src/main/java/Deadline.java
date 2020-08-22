package main.java;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime localDateTime;
    protected String date;

    public Deadline(String description, String by) {
        super(description);
        this.date = by;
        by = by.replace('/', '-');
        this.localDateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern(
                "[d-MM-yyyy HHmm][yyyy-MM-d HHmm]"));
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
                localDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm a")) + ")";
    }
}
