package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime date;
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy hhmm");

    /**
     * Instantiate an event with a date and description
     * @param work Description of the event
     * @param date Date of the event
     */
    public Event(String work, LocalDateTime date) {
        super(work, date);
        this.date = date;
    }

    public String toString() {
        return "[E]" + super.toString() + "(at: " + date.format(format) + ")";
    }

    /**
     * Get a description of the event
     * @return String containing tge description of the event
     */
    public String description() {
        return super.isDone()
                ? "[E][0]"
                : "[E][1]";
    }
}
