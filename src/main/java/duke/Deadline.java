package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is one type of tasks.
 * It contains deadline for the task.
 */
public class Deadline extends Task {

    protected LocalDate deadline;

    /**
     * Constructor of Deadline.
     *
     * @param description Description of task.
     * @param deadline Deadline of task.
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns string representation of Deadline.
     *
     * @return String representation of deadline.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
