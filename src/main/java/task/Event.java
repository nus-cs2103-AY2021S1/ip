package task;
/**
 * A subclass of Task.
 * Contains a task description and a time.
 */
public class Event extends Task {
    private String description;
    private boolean isDone;
    private String at;

    /**
     * Constructor of Event object.
     * @param description description of the task.
     * @param at the time of the event.
     */
    public Event(String description, String at) {
        this.description = description;
        this.isDone = false;
        this.at = at;
    }

    /**
     * Constructor of Event object.
     * @param description description of the task.
     * @param isDone the status of the task.
     * @param at the time of the event.
     */
    public Event(String description, boolean isDone, String at) {
        this.description = description;
        this.isDone = isDone;
        this.at = at;
    }

    /**
     * Returns the description of the deadline task.
     * @return the description of the deadline task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the icon for corresponding status of task.
     * @return sign of tick or cross.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }
    /**
     * Marks the event task as done.
     * @return new Deadline object with true for isDone.
     */
    @Override
    public Event markAsDone() {
        return new Event(this.description, true, this.at);
    }

    /**
     * Turns task object into a string to be saved in data file.
     * @return string in the format of data in data file.
     */
    public String toStringOfDatabase() {
        String number = isDone ? "1" : "0";
        return "E | " + number + " | " + this.description + " | " + this.at;
    }

    /**
     * Prints object.
     * @return string of object.
     */
    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + this.description + " (at: " + at + ")";
    }
}
