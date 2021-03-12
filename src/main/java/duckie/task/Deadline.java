package duckie.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task type in charge of task containing deadline date.
 */
public class Deadline extends Task implements Comparable<Deadline> {
    protected static final DateTimeFormatter DT_FORMATTER = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
    protected LocalDate deadline;

    /**
     * Instantiatess a Deadline task.
     *
     * @param description Description of the task.
     * @param deadline Deadline date of the task.
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Overrides method to return the type of the Deadline task.
     *
     * @return "D" string.
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Overrides method to return the date of the Deadline task.
     *
     * @return Date string of Deadline Task.
     */
    @Override
    public String getDate() {
        return DT_FORMATTER.format(this.deadline);
    }

    /**
     * Overrides method to return the String representation of a Deadline task.
     *
     * @return String representation of Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DT_FORMATTER.format(this.deadline) + ")";
    }

    /**
     * Compare the date of two deadline tasks.
     *
     * @param task2 Task to be compared with.
     * @return 1 if current task is later, -1 if current task is earlier and
     * 0 if current task and task2 have the same dates.
     */
    @Override
    public int compareTo(Deadline task2) {
        return this.deadline.compareTo(task2.deadline);
    }
}
