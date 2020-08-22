package main.java;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        this(description, by, false);
    }

    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String saveFormat() {
        return "D" + "~" + super.saveFormat() + "~" + this.by;
    }

    public boolean hasSameDate(LocalDate theDate) {
        return by.toLocalDate().equals(theDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(this.DATE_FORMAT) + ")";
    }
}