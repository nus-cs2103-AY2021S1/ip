package main.java;

import java.time.LocalDate;

class Deadline extends Task {

    Deadline(String description, boolean isComplete, LocalDate date) {
        super(description, isComplete, date);
    }

    @Override
    String[] getDataString() {
        return new String[] {"deadline", String.valueOf(this.isComplete), this.description, this.date.toString()};
    }

    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description + " (by: " + super.getDateString() + ")";
    }
}
