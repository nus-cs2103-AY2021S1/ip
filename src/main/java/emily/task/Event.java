package emily.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents an Event task which has a timestamp at a given time
 * and description
 */
public class Event extends Task {

    protected LocalDateTime at;
    protected String timeStamp;

    /**
     * Subclass of Task with a timestamp
     *
     * @param description
     * @param timeStamp
     */
    public Event(String description, String timeStamp) {
        super(description);
        this.timeStamp = timeStamp;
        this.at = LocalDateTime.parse(timeStamp, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.type = 'E';
    }

    /**
     * Getter method to retrieve timestamp
     *
     * @return String of the date in the form yyy-mm-dd
     */
    public String getAt() {
        return timeStamp;
    }

    @Override
    public String toString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        return "[E]" + super.toString()
                + " (at: " + this.at.format(formatter) + ")";
    }
}
