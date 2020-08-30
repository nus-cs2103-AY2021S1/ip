package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 *  The EventTask class inherits method from the Task class.
 *  It would create an EventTask object with the given description
 *  and date-time.
 */
public class EventTask extends Task {
    private static final String EVENT = "[E]";
    private LocalDateTime dateTime;

    /**
     * Constructs a new EventTask with empty description
     * and null as its date time.
     */
    public EventTask() {
        super("");
        this.dateTime = null;
    }

    /**
     * Constructs a new EventTask with task description
     * and task date time that was parsed from user command.
     *
     * @param description String event task description.
     * @param dateTime LocalDateTime event task date and time.
     */
    public EventTask(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Gets task date-time.
     *
     * @return LocalDateTime This return event task date-time.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Sets set the current date-time to the given date-time.
     *
     * @param dateTime LocalDateTime this argument would change the
     * current date-time.
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Returns messages to user. The result is the concatenation of :
     * 1. The type of the Task.
     * 2. The task's status.
     * 3. The description of the task.
     * 4. The date and time of the task (MMM d yyy, h a).
     *
     * @return String Returns the task type ([E]), its status as well
     * as its description and date-time in an intended format.
     */
    @Override
    public String toString() {
        return EVENT + super.toString() + " (by: "
                + this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyy, h a")) + ")";
    }
}
