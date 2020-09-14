package duke.task;

/**
 * Represent a Event object
 * A <code>Event</code> corresponds to a task created using the command
 * "event"
 */
public class Event extends Task {

    protected String at;

    /**
     * Constructor of the Event Class
     *
     * @param description description of the task
     * @param at date of the task
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructor of the Event Class
     *
     * @param description description of the task
     * @param status status of the task
     * @param at date of the task
     */
    public Event(String description, int status, String at) {
        super(description, status);
        this.at = at;
    }

    /**
     * Returns a string to be written inside the text file
     *
     * @param status current status of the task
     * @return string representation of the task
     */
    @Override
    public String saveText(int status) {
        return "E | " + status + " | " + description + " | " + at;
    }

    /**
     * Returns a string representation of a task object
     *
     * @return string representation of a task object
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
