import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        super(desc.split("event ")[1].split(" /at ")[0], "E");
        this.timing = desc.split("event ")[1].split(" /at ")[1];
        try {
            String[] split = this.timing.split(" ");
            LocalDate date = LocalDate.parse(split[0]);
            String restOfTime = this.timing.split(split[0])[1];
            this.timing = date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + restOfTime;
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
