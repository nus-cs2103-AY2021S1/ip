package Duke;

/**
 * The Event class that represents a event task.
 *
 * @author Zeng Yu Ting
 * @version 1.0
 * @since 2020-15-08
 */
public class Event extends Task {
    private String dueDate;
    public Event(String description, String dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }

    public String toString() {
        return "[E]" + super.toString() + "(at: " + dueDate + ")";
    }

    /**
     * This method returns the string to be written for the event.
     */
    public String toWriteString() { return "E " +  super.toWriteString() + " | " + dueDate; }
}
