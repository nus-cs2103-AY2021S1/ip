package duke.task;


/**
 * Represents an event task with description and at-time.
 * Inherits from Task.
 */
public class Event extends Task {
    protected final String at;

    public Event(String desc, String at) {
        super(desc);
        this.at = at;
    }

    /**
     * Converts the task to a string for saving.
     *
     * @return String representing a task in save file.
     */
    @Override
    public String toSaveFormat() {
        return "event " + super.toSaveFormat() + " /at " + at;
    }

    /**
     * Converts the task to a string indicating type of task, done, description and time (if applicable).
     *
     * @return String representing task in user interface.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
