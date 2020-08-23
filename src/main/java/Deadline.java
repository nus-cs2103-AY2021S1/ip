package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.by = LocalDate.parse(by);
    }

    public Deadline(String description, boolean isDone, String by) throws DateTimeParseException {
        super(description, isDone);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String getPlainText() {
        return super.getPlainText() + " | " + by;
    }

    @Override
    public String toString() {
        String formattedBy = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedBy + ")";
    }
}