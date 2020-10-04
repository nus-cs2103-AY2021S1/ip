package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates an event task.
 */
public class Event extends Task {

    protected LocalDate at;

    /**
     * Creates an event task with a description and the date of the event, not done by default.
     * @param description Description of task.
     * @param at Date of the event.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Creates an event task with a description, a done status, and the date of the event.
     * @param description Description of task.
     * @param isDone Done status of task.
     * @param at Date of the event.
     */
    protected Event(String description, boolean isDone, LocalDate at) {
        super(description, isDone);
        this.at = at;
    }

    protected void updateTaskDescription(String newDescription) {
        this.description = newDescription;
    }

    protected void updateTaskTime(String newTime) throws YooException {
        try {
            this.at = LocalDate.parse(newTime);
        } catch (DateTimeParseException e) {
            throw new YooException("Date format should be yyyy-mm-dd!\nPlease try again (>_<)");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
