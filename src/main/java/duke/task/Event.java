package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A sub class of Task, which represents any events.
 */
public class Event extends Task {
    private LocalDateTime date;

    /**
     * Event constructor.
     *
     * @param description Details of the task.
     * @param isDone      Progress of the task.
     * @param date        Date of the event.
     */
    public Event(String description, boolean isDone, LocalDateTime date) {
        super(description, isDone);
        this.date = date;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return "[E]"
                + super.getStatusIcon()
                + " "
                + super.toString()
                + "(at: "
                + date.format(DateTimeFormatter.ofPattern("MMM d, yyyy HH:mm"))
                + ")";
    }
}
