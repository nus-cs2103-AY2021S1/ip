package main.java;

public class Event extends Task {

    protected String date;

    // Constructor for Event
    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    // Return string representation of Event
    @Override
    public String toString() {
        return " [E]" + super.toString() + " (at: " + date + ")";
    }
}
