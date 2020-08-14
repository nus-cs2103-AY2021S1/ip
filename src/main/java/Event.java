package main.java;

public class Event extends Task {
    public Event(String description, String time) {
        super(description + " (at: " + time + ")");
    }

    @Override
    public String getTaskType() {
        return "E";
    }
}
