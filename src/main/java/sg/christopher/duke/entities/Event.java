/**
 * Wrapper class for Event tasks.
 */
package sg.christopher.duke.entities;

public class Event extends Task {
    /**
     * Creates a new Event task.
     *
     * @param description description of task
     * @param dateTime time that task needs to be done at
     */
    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    private String dateTime;

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + dateTime + ")";
    }
}
