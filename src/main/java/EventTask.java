package main.java;

public class EventTask extends Task {
    private String time;
    EventTask(String taskName, String time) {
        super(taskName);
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
