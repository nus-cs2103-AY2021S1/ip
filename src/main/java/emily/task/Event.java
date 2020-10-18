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
     * @param description detailing the name of the task
     * @param timeStamp   in the form of yyyy-mm-dd hhmm
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
     * @return String of the date in the form yyyy-mm-dd
     */
    public String getAt() {
        return timeStamp;
    }

    @Override
    public String toString() {

        //timestamp formatted into a date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");

        return "[E]" + super.toString()
                + " (at: " + this.at.format(formatter) + ")";
    }
}
