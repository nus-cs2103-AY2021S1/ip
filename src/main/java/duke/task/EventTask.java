package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidDateException;

/**
 * Represents an event task.
 */
public class EventTask extends Task {

    /**
     * Date of event.
     */
    private LocalDateTime timing;

    /**
     * Constructs a new instance of an EventTask.
     * @param description Description of event task.
     * @param timing Timing of event task.
     * @throws InvalidDateException If timing is not in the form of "dd-MM-yyyy HH:mm".
     */
    public EventTask(String description, String timing) throws InvalidDateException {
        super(description);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            this.timing = LocalDateTime.parse(timing, formatter);
        } catch (DateTimeParseException exception) {
            throw new InvalidDateException();
        }
    }

    public LocalDateTime getTiming() {
        return this.timing;
    }

    @Override
    public String toString() {
        return getPriority().isEmpty()
            ? "[E][" + getStatusIcon() + "] " + description + " (by: "
                + timing.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a")) + ")"
            : "[E][" + getStatusIcon() + "] " + description + " (by: "
                + timing.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a")) + ")"
                + " Priority: " + getPriority();
    }

}
