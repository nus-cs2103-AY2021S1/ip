package main.java.duke.task;

import java.time.LocalDate;

public class Deadline extends Task {

    public Deadline(String description, boolean isComplete, LocalDate date) {
        super(description, isComplete, date);
    }

    @Override
    public String[] getDataString() {
        return new String[] {"deadline", String.valueOf(this.isComplete), this.description, this.date.toString()};
    }

    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description + " (by: " + super.getDateString() + ")";
    }
}
