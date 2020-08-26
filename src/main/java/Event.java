/**
 * Encapsulates a task with a specific time
 */

public class Event extends Task {
    protected String at;

    /**
     * Constructor
     *
     * @param description is the description of the task
     * @param at is the time that the task is happening at
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructor
     *
     * @param description is the description of the task
     * @param isDone is whether or not the task is done
     * @param at is the time that the task is happening at
     */
    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Gives the String representation of the Task
     *
     * @return a String
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
