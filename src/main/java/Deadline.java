import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task which has a deadline.
 */
public class Deadline extends Task{

    private String task;
    private LocalDate deadline;

    protected Deadline(String task, LocalDate deadline) {
        this.task = task;
        this.deadline = deadline;
    }

    /**
     * Returns the task description of this Deadline.
     *
     * @return String of task description.
     */
    @Override
    protected String getTask() {
        return this.task;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                super.isDone() ? "\u2713" : "\u2717",
                getTask(),
                deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
    }

}
