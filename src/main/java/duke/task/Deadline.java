package main.java.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDate by;
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String record() {
        if(this.isCompleted) {
            return "D | 1 | " + description + " | " + by;
        } else {
            return "D | 0 | " + description + " | " + by;
        }
    }

    @Override
    public boolean isAt(LocalDate localDate) {
        return this.by.equals(localDate);
    }
}
