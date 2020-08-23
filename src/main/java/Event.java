package main.java;

public class Event extends Task {
    String period;
    public Event (String description, String period) {
        super(description);
        this.period = period;
    }

    public Event(boolean isDone, String description, String period) {
        super(isDone, description);
        this.period = period;
    }

    String getPeriod() {
        return this.period;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription() + " (at: " + getPeriod() +")";
    }

    @Override
    public String saveFormat() {
        if (isDone) {
            return "E | 1 | " + this.getDescription() + " | " + this.getPeriod();
        } else {
            return "E | 0 | " + this.getDescription() + " | " + this.getPeriod();
        }
    }
}

