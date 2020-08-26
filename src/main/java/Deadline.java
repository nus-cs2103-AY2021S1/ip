import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Deadline class Represents an Deadline task that contains a description along with the date and
 * time.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructor that takes in the deadline description and the date/time
     * @param description event description
     * @param by date/time of event
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a modified string representation of the date/time
     * @return modfied string representation of the date/time
     */
    public String getTime() {
        try {
            Date d1 = new SimpleDateFormat("dd/mm/yyyy HHmm").parse(by);
            return new SimpleDateFormat("HH:mm, dd MMM yyyy").format(d1);
        } catch (ParseException e) {
            System.out.println("Invalid format");
        }
        return "";
    }

    /**
     * Returns task type
     * @return "D"
     */
    public String getType() {
        return "D";
    }

    /**
     * Returns the string representation of the date/time
     * @return original string representation of date/time
     */
    public String getBy() { return by; }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getTime() + ")";
    }
}