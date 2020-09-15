import java.util.Date;

/**
 * Event is a type of Task but with a date when it is held.
 */
public class Event extends Task {

    private final Date at;

    /**
     * Creates a new Event.
     *
     * @param description Description of Event.
     * @param at          Time of Event.
     * @param isDone      true if event is over, false if event if in the future.
     */
    public Event(String description, Date at, boolean isDone, Priority priority) {
        super(description, TaskType.EVENT, isDone, priority);
        this.at = at;
    }

    @Override
    public String getSavedString() {
        return super.getSavedString() + " | " + Ui.formatDate(at);
    }

    @Override
    public boolean isOccuringOn(Date date) {
        return date.equals(at);
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + Ui.formatDate(at) + ")" + (priority == null ? ""
                : " (priority: " + priority.toString() + ")");
    }

}
