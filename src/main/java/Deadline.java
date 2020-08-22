package main.java;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime date;

    public Deadline(String description, String by) {
        super(description);
        by = by.replace('/', '-');
        this.date = LocalDateTime.parse(by, DateTimeFormatter.ofPattern(
                "[d-MM-yyyy HHmm][yyyy-MM-d HHmm]"));
    }

    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("d-MM-yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm a")) + ")";
    }
}
