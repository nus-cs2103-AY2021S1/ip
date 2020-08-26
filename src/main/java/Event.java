package main.java;

import java.time.LocalDate;

class Event extends Task {
    protected LocalDate date;

    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    @Override
    public String getTypeOfTask() {
        return "event";
    }

    @Override
    public String toString() {
        return "[E] [" + this.getStatusIcon() + "] " + this.description + " ----- When: " + this.date;
    }
}
