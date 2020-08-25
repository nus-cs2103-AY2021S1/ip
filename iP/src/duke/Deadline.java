package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is one type of tasks.
 * It contains deadline for the task.
 */
public class Deadline extends Task{

    protected LocalDate deadline;

    /**
     * constructor of Deadline
     * @param description description of task
     * @param deadline deadline of task
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * represents Deadline in string
     * @return string representation of deadline
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
