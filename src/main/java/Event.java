package main.java;

public class Event extends Task {
    String time;

    Event(String description, String time) {
        this(description, false, time);
    }

    Event(String description, boolean isDone, String time) {
        super(description, isDone);
        taskType = TaskType.EVENT;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
