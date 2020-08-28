package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Inherits from task and represents a task with a deadline.
 */
public class DeadlineTask extends Task {
    /**
     * Deadline of the task.
     */
    private final LocalDateTime deadline;

    /**
     * Creates a deadline task.
     *
     * @param description Description of the task.
     * @param isDone      State of whether the task is done.
     * @param deadline    Deadline of the task.
     */
    public DeadlineTask(String description, boolean isDone, String deadline) {
        super(description, isDone);
        String[] splitDeadline = deadline.split(" ");
        String inputDeadline = splitDeadline[0] + "T" + splitDeadline[1].substring(0, 2) + ":" + splitDeadline[1].substring(2, 4);
        this.deadline = LocalDateTime.parse(inputDeadline);
    }

    /**
     * Return a string representation of the deadline task.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mma")) + ")";
    }
}
