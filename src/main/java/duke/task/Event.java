package duke.task;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Encapsulate the event class
 */
public class Event extends Task {
    private Date time;
    private SimpleDateFormat formatter;

    /**
     * Creates an event
     * @param desc Description of the event
     * @param time Time of the event
     */
    public Event(String desc, Date time) {
        super(desc);
        this.time = time;
        formatter = new SimpleDateFormat ("MMM dd yyyy hh:mm a");
    }

    /**
     * Returns the string representation
     * @return String representation
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), formatter.format(time));
    }

    /**
     * Returns the string to be saved
     * @return String to be saved
     */
    @Override
    public String toFileString() {
        return "E\n"+super.toFileString()+formatter.format(time) + "\n";
    }
}
