package main.java;

/**
 * A kind of the tasks, which has a specific time period
 */
public class Event extends Task {
    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public Event(String description, String time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String getDescription() {
        return "[E][" + getStatusIcon()
                + "]" + description + "(at:" + time + ")";
    }

    @Override
    public void printDescription() {
        System.out.println(getDescription());
    }
}
