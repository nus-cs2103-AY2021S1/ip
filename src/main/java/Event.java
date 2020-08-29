/**
 * A subclass of Task.
 * Contains a task description and a time.
 */
public class Event extends Task {
    protected String at;

    /**
     * Constructor of Event object.
     * @param description description of the task.
     * @param at the time of the event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructor of Event object.
     * @param description description of the task.
     * @param isDone the status of the task.
     * @param at the time of the event.
     */
    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
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
    @Override
    public String stringify() {
        String number = isDone ? "1" : "0";
        return "E | " + number + " | " + super.description + " | " + this.at;
    }

    /**
     * Prints object.
     * @return string of object.
     */
    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (at: " + at + ")";
    }
}
