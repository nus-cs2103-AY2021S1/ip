package main.java;

public class Event extends Task {
    String day;

    public Event(String taskName, String day) {
        super(taskName);
        this.day = day;
    }

    @Override
    public String toString() {
        String finished = this.done ? "✓" : "✗";
        String toReturn = "[E]" + "[" + finished + "] " + taskName + " (at: " + day + ")";
        return toReturn;
    }
}
