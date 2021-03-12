import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event marked by the user
 * that has time information stored.
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Constructor for an event.
     * @param description Description of the event.
     * @param at String containing time information of the event.
     * @return nothing
     */
    public Event(String description, String at) {
        super(description);
        this.type = "E";
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.at = LocalDate.parse(at,inputFormat);
    }

    /**
     * Constructor for an event.
     * @param description Description of the event.
     * @param isDone Boolean value indicating if the event is completed.
     * @param at String containing time information of the event.
     * @return nothing
     */
    public Event(String description, boolean isDone, String at) {
        super(description, isDone,"E");
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.at = LocalDate.parse(at,inputFormat);
    }

    @Override
    public String toString() {
        String atTime = at.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return super.toString() + "(at: " + atTime + ")" +
                (this.tag == null ? "" : (" <" + this.tag + ">"));

    }
}