package duke.task;

/**
 * Class to add an Event, which is a Task that requires a datetime.
 */
public class Event extends Task {
    protected String eventDate;

    /**
     * Creates an Event.
     *
     * @param description Description of the Event.
     * @param eventDate DateTime of the Event.
     */
    public Event(String description, String eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    /**
     * Reformat the Event from existing data.
     *
     * @return Reformatted Event for writing to storage.
     */
    public String formatEvent() {
        return String.format("E | %d | %s | %s",
                this.getIsDone() ? 1 : 0, this.getDescription(), this.eventDate);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.eventDate + ")";
    }
}