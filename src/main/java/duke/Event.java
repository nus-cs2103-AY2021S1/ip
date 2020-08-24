package main.java.duke;

import java.time.LocalDate;

class Event extends Task {

    Event(String description, boolean isComplete, LocalDate date) {
        super(description, isComplete, date);
    }

    @Override
    String[] getDataString() {
        return new String[] {"deadline", String.valueOf(isComplete), this.description, this.date.toString()};
    }

    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description + " (at: " + super.getDateString() + ")";
    }
}
