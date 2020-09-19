package juke.task;

/**
 * Represents a Task that occurs at a given event date.
 */
public class Event extends Task {

    private TaskDate at;

    /**
     * Constructs an Event Task with an input description and at Date.
     * @param description
     * @param at
     */
    public Event(String description, TaskDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the representative text of the Event.
     * @return Representative text.
     */
    @Override
    public String taskSaver() {
        String type = "E";
        return String.format("%s/%s/%s",
                type,
                super.taskSaver(),
                at.saveDateToDisk());
    }

    /**
     * Sets the At date to given date
     * @param at New Date to be changed to.
     */
    public void setAtDate(TaskDate at) {
        this.at = at;
    }

    /**
     * Outputs the Event as a String.
     * @return String representation of Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.at + ")";
    }
}
