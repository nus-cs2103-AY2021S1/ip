import java.util.Date;
import java.util.Optional;

/**
 * Event object is a subclass of Task object. It contains a time as String,
 * DateManager to process the time and an Optional to store a Date object
 * if time is of a valid format.
 *
 * @author Hakiem Rasid
 *
 */
public class Event extends Task {

    private String time;
    private Optional<Date> optTime;
    private DateManager dateManager;

    /**
     * Constructor for Event object.
     *
     * @param name Description of event.
     * @param deadline Description of time of this event.
     */
    public Event(String name, String time) {
        super(name);
        this.time = time;
        this.dateManager = new DateManager();
        this.optTime = dateManager.getDate(time);
    }

    /**
     * Returns time of this event.
     *
     * @return Time as a String.
     */
    public String getTime() {
        return this.time;
    }

    /**
     * Prints a String representation of Event object and processes validity of time
     * format to determine format of output.
     * Clearly labels the Deadline object to be easily distinguishable from other
     * Task objects.
     *
     * @return String representation of Event.
     */
    @Override
    public String printTask() {
        StringBuilder sb = new StringBuilder();
        sb.append("[E]");
        sb.append(super.printTask());
        if (!optTime.isPresent()) {
            sb.append(" (at: " + this.time + ")");
        } else {
            sb.append(" (by: " + dateManager.getDateAsString(time) + ")");
        }
        return sb.toString();
    }

}