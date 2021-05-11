
import java.time.format.DateTimeParseException;

/**
 * The {@code Event} class extends the {@code Task} class.
 * Stores the date/time of the event as well.
 */
public class Event extends Task {

    private String timing;

    /**
     * Initialises an Event task and separates the {@code desc}
     * into the Event's description and date/time.
     *
     * @param desc Full description of Event inclusive of time/date.
     */
    public Event(String desc) {
        super(Task.getDescriptionFromStringInput(desc, TimeConstraintKeyword.EVENT_KEYWORD), "E");
        this.timing = Task.getTimeConstraintFromStringInput(desc, TimeConstraintKeyword.EVENT_KEYWORD);
        try {
            this.timing = tryFormatDateElseThrow(timing);
        } catch (DateTimeParseException e) {
            // no date
        }
    }

    /**
     * Initialises an Event task.
     *
     * @param desc   Description of Event task.
     * @param timing Date/time of Event.
     */
    public Event(String desc, String timing) {
        super(desc, "E");
        this.timing = timing;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + timing + ")";
    }

    /**
     * Returns date/time of Event task.
     *
     * @return Date/time of Event.
     */
    @Override
    public String getTiming() {
        return this.timing;
    }
}
