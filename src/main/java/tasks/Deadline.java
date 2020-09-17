package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the deadlines that the user wants to remember.
 */
public class Deadline extends Task {

    protected LocalDateTime deadline;

    /**
     * Constructor for creating a Deadlines object
     *
     * @param taskDesc Description of the Deadlines.
     * @param deadline Deadline of task.
     */
    public Deadline(String taskDesc, LocalDateTime deadline) {
        super(taskDesc);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + deadline.format(DateTimeFormatter.ofPattern("HH:mm MMM d yyyy")) + ")";
    }
}
