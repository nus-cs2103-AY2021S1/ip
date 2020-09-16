package duke.task;

/**
 * An event is a task which occurs at a particular date and time.
 */
public class Event extends Task {

    private String eventDate;

    /**
     * Initialise an event with description and date of the event.
     * @param description Description of the event.
     * @param date Date of the event.
     */
    public Event(String description, String date) {
        super(description);
        this.eventDate = date;
    }

    /**
     * Initialise an event with description and date of the event.
     * @param description Description of the event.
     * @param date Date of the event.
     * @param isDone Whether the event is completed.
     */
    public Event(String description, String date, boolean isDone) {
        super(description, isDone);
        this.eventDate = date;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + eventDate + ")";
    }

    @Override
    public String getDescriptionForDatabase() {
        return "event - " + (isTaskDone() ? "1" : "0") + " - "
                + getDescription() + " - " + eventDate;
    }
}
