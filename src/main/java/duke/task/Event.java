package duke.task;

import java.time.LocalDateTime;

public class Event extends Task {

    private final String timeFrame;

    /**
     * Initializes an {@code Event} task with the given description and time frame.
     *
     * @param description Description of event.
     * @param timeFrame Time frame of event.
     */
    public Event(String description, String timeFrame) {
        super(description, TaskType.EVENT, timeFrame, LocalDateTime.now(), false);
        this.timeFrame = timeFrame;
    }

    /**
     * Initializes an {@code Event} task with all parameters.
     *
     * @param description Description of event.
     * @param isDone Boolean status of event.
     * @param timeFrame Time frame of event.
     * @param dateTime DateTime representing when the Event was created.
     */
    public Event(String description, boolean isDone, String timeFrame, LocalDateTime dateTime) {
        super(description, TaskType.EVENT, timeFrame, dateTime, isDone);
        this.timeFrame = timeFrame;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + timeFrame + ")";
    }
}
