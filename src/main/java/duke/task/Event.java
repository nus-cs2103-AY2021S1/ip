package duke.task;

/**
 * Represents a Event Task.
 */
public class Event extends Task {
    private String time;

    /**
     * Constructor for an Event Task.
     */
    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Constructor for an Event Task.
     */
    public Event(boolean isDone, String description, String time) {
        super(isDone, description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + time + ")";
    }

    @Override
    public String toStoreFormat() {
        String type = "E";
        String status = super.isDone ? "1" : "0";
        String connect = " | ";
        return type + connect + status + connect + description + connect + time;
    }

}
