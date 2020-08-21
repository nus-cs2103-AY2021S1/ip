package main.java;

public class Event extends Task {
    String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public Event(String description, String time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + time + ")";
    }

    @Override
    public String simplifiedTaskString() {
        return super.simplifiedTaskString() + " - " + this.time;
    }

    @Override
    public String getTaskType() {
        return "E";
    }
}
