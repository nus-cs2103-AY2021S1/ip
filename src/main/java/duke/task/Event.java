package duke.task;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is a event type of task. Users are able to store the event time.
 */
public class Event extends Task {

    private LocalDateTime taskDateTime;

    /**
     * Constructs a Event-type task with a description and event time.
     *
     * @param desc The description of the event.
     * @param taskDateTime The date and time of this event occurring.
     */
    public Event(String desc, LocalDateTime taskDateTime) {
        super(desc);
        this.taskDateTime = taskDateTime;

        if (this.desc.isBlank() || this.taskDateTime == null)
            throw new DukeException("The description or date of \"event\" cannot be empty");

    }

    /**
     * {@inheritDoc}
     */
    public String getSaveToFileString() {
        return "E`" + super.getSaveToFileString() + "`" + taskDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                this.taskDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")));
    }
}
