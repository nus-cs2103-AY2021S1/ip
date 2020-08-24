package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {

    protected LocalDateTime at;
    String originalDate;

    /**
     * Class constructor without extra arguments.
     * @param description String description of event task.
     * @param at String argument for date of event task.
     */
    public Event(String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at,
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        originalDate = at;
    }

    /**
     * Class constructor with extra boolean argument.
     * @param description String description of event task.
     * @param isDone Boolean representing whether task is done.
     * @param at String argument for date of event task.
     */
    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = LocalDateTime.parse(at,
                DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        originalDate = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: "
                + at.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a"))
                + ")";
    }

    @Override
    public String write() {
        return "\nevent," + super.write() + "," + originalDate;
    }
}
