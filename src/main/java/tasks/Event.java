package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the events that the user inputs.
 */
public class Event extends Task {

    protected LocalDateTime timing;

    /**
     * Constructor for creating an Events object.
     *
     * @param taskDesc Description of the task.
     * @param timing Timing to finish the task.
     */
    public Event(String taskDesc, LocalDateTime timing) {
        super(taskDesc);
        this.timing = timing;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + timing.format(DateTimeFormatter.ofPattern("HH:mm MMM d yyyy")) + ")";
    }
}
