package taskbot.task;

public class Event extends Task {
    private String at;

    /**
     * Creates an incomplete Event task
     * @param task Description of task
     * @param at Time when task occurs
     */
    public Event(String task, String at) {
        super(task);
        this.at = at;
    }

    /**
     * Creates an Event task given its completeness
     * @param task Description of task
     * @param at Time when task occurs
     * @param isDone Whether the task is complete
     */
    public Event(String task, String at, boolean isDone) {
        super(task, isDone);
        this.at = at;
    }

    /**
     * Gets the time the event occurs.
     * @return The time of the event.
     */
    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[" + super.getStatusIcon() + "] " + "Event: " + super.getTask() +
                "(at: " + at + ")" + "\n";
    }
}
