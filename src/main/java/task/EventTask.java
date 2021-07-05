package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import util.SplitOperation;

/**
 * Inherits from task and represents a task with an event time.
 */
public class EventTask extends Task {
    /**
     * Event time of the task.
     */
    private final LocalDateTime eventTime;

    /**
     * Creates an event task.
     *
     * @param description Description of the task.
     * @param isDone      State of whether the task is done.
     * @param eventTime   Event time of the task.
     */
    public EventTask(String description, boolean isDone, String eventTime) {
        super(description, isDone);
        // formats eventTime date and time to the correct format, for example: 2007-12-03T10:15:30
        SplitOperation eventSplit = (input) -> {
            String[] splitInput = input.split(" ");
            return splitInput[0] + "T" + splitInput[1].substring(0, 2) + ":"
                    + splitInput[1].substring(2, 4);
        };
        this.eventTime = LocalDateTime.parse(eventSplit.operate(eventTime));
    }

    /**
     * Return a string representation of the event task.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "\n(at: " + eventTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mma"))
                + ")";
    }
}
