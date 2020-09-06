package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The DeadlineTask class inherits method from the Task class.
 * It would create an DeadlineTask object with the given description
 * and date-time.
 */
public class DeadlineTask extends Task {
    private static final String DEADLINE = "[D]";
    private LocalDateTime dateTime;

    /**
     * Constructs a new DeadlineTask with empty description
     * and null as its date time.
     */
    public DeadlineTask() {
        super("");
        this.dateTime = null;
    }

    /**
     * Constructs a new DeadlineTask with task description
     * and task date time that was parsed from user command.
     *
     * @param description String deadline task description.
     * @param dateTime LocalDateTime deadline task date and time.
     */
    public DeadlineTask(String description, LocalDateTime dateTime) {
        super(description);

        assert dateTime != null : "DateTime is null";
        this.dateTime = dateTime;
    }

    /**
     * Gets task date-time.
     *
     * @return LocalDateTime This return deadline task date-time.
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
    public void setDate(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Returns messages to user. The result is the concatenation of :
     * 1. The type of the Task.
     * 2. The task's status.
     * 3. The description of the task.
     * 4. The date and time of the task (MMM d yyy, h a).
     *
     * @return String Returns the they task type ([D]), its status as well
     * as its description and date-time in an intended format.
     */
    @Override
    public String toString() {
        return DEADLINE + super.toString() + " (by: "
                + this.dateTime.format(DateTimeFormatter.ofPattern("MMM d yyy, h a")) + ")";
    }
}
