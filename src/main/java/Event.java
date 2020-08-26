import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Event class
 * Represents an event task that contains a description
 * along with the date and time.
 */
public class Event extends Task {

    protected String at;

    /**
     * Constructor that takes in event description and the date/time
     * @param description event description
     * @param at date/time of event
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the string representation of the date/time
     * @return original string representation of date/time
     */
    public String getAt() { return at;}

    /**
     * Returns a modified string representation of the date/time
     * @return modified string representation of date/time
     */
    public String getTime() {
        try {
            Date d1 = new SimpleDateFormat("dd/mm/yyyy HHmm").parse(at);
            return new SimpleDateFormat("HH:mm, dd MMM yyyy").format(d1);
        } catch (ParseException e) {
            System.out.println("Invalid format");
        }
        return "";
    }

    /**
     * Returns task type
     * @return "E"
     */
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getTime() + ")";
    }
}