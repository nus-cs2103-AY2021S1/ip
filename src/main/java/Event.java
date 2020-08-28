import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Event class as the child class of Task
 * contains information such as the date and time of the event, and event name
 */
public class Event extends Task implements Serializable {

    protected LocalDateTime eventDateTime;

    /**
     * Constructor of Event
     *
     * @param eventName
     * @param eventDateTime
     */
    public Event(String eventName, LocalDateTime eventDateTime) {
        super(eventName);
        this.eventDateTime = eventDateTime;
    }

    /**
     * formats the user input
     *
     * @return the formatted output of date and time
     * @throws DateTimeParseException when the user input format of the date and time is wrong
     */
    public String getEventDateTime() throws DateTimeParseException {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern(" dd-MMM-yyyy hh:mm a", Locale.ENGLISH);
        return eventDateTime.format(outputFormat);
    }

    /**
     * shows the event description, event date and time, and event icon
     *
     * @return the string format of Event
     */
    @Override
    public String toString() {
        return "[E]" + "[" + getStatusIcon() + "] " + getDescription() + "(at:" + getEventDateTime() + ")";
    }
}
