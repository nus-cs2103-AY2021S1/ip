package seedu.duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that represents a task that has a deadline.
 */
public class Deadline extends Task {
    private LocalDateTime time;

    public Deadline(String name, LocalDateTime time, boolean status) {
        super(name, status);
        this.time = time;
    }

    /**
     * Returns what the current task is and its status.
     * @return String representing the task.
     */
    @Override
    public String getStatus() {
        return "[D]" + super.getStatus() + " (by: "
                + this.time.format(DateTimeFormatter.ofPattern("d MMM yyyy kkmm")) + ")";
    }

    /**
     * Gets the deadline of the task.
     * @return DateTime object that represents the deadline.
     */
    @Override
    public LocalDateTime getTime() {
        return this.time;
    }

    @Override
    public LocalDateTime getEndTime() {
        return null;
    }

    /**
     * Returns the type of the task.
     * @return String that represents the type of the task.
     */
    @Override
    public String getType() {
        return "D";
    }
}
