package main.java;

public class Event extends Task {
    String period;
    public Event (String description, String period) {
        super(description);
        this.period = period;
    }

    String getPeriod() {
        return this.period;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription() + " (at: " + getPeriod() +")";
    }
}

