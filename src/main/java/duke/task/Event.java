package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {

    protected LocalDateTime dateTime;
    private String originalDate;

    /**
     * Class constructor without extra arguments.
     * @param description String description of event task.
     * @param dateTime String argument for date of event task.
     */
    public Event(String description, String dateTime) {
        super(description);
        this.dateTime = LocalDateTime.parse(dateTime,
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        originalDate = dateTime;
    }

    /**
     * Class constructor with extra boolean argument.
     * @param description String description of event task.
     * @param isDone Boolean representing whether task is done.
     * @param dateTime String argument for date of event task.
     */
    public Event(String description, boolean isDone, String dateTime) {
        super(description, isDone);
        this.dateTime = LocalDateTime.parse(dateTime,
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        originalDate = dateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: "
                + dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a"))
                + ")";
    }

    @Override
    public String write() {
        return "\nevent," + super.write() + "," + originalDate;
    }
}
