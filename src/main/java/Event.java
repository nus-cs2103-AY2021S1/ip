package main.java;

public class Event extends Task {
    String duration;

    Event(String title, String duration) {
        super(title);
        this.duration = duration;
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.duration + ")";
    }
}
