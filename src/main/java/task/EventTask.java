package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Inherits from task and represents a task with an event time.
 */
public class EventTask extends Task {
    /** Event time of the task. */
    private final LocalDateTime eventTime;

    /**
     * Creates an event task.
     *
     * @param description Description of the task.
     * @param isDone State of whether the task is done.
     * @param eventTime Event time of the task.
     */
    public EventTask(String description, boolean isDone, String eventTime) {
        super(description, isDone);
        String[] splitEventTime= eventTime.split(" ");
        String inputEventTime = splitEventTime[0] + "T" + splitEventTime[1].substring(0,2) + ":" + splitEventTime[1].substring(2,4);
        this.eventTime = LocalDateTime.parse(inputEventTime);
    }

    /**
     * Return a string representation of the event task.
     * 
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mma")) + ")";
    }
}
