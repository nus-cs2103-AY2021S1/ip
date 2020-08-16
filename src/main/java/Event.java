package main.java;

public class Event extends Task {
    protected String date;
    public Event(String description, String date) {
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
