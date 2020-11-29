import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class representing a timed task.
 * @author vanGoghhh
 */

public abstract class TimedTask extends Task {

    private LocalDate dueDate;

    /**
     * Constructor for a timed task.
     * @param description details of the task.
     * @param dueDate deadline of the task.
     */
    public TimedTask(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Returns duedate of the task.
     * @return duedate of the task.
     */
    protected LocalDate getTaskDeadline() {
        return this.dueDate;
    }

    /**
     * Returns string representation of a timed task.
     * @return String representation of a timed task.
     */
    @Override
    public String toString() {
        if (this instanceof Event) {
            return "[E]" + super.toString() + " (at: "
                    + getTaskDeadline().format(DateTimeFormatter.ofPattern("d MMM uuuu")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: "
                    + getTaskDeadline().format(DateTimeFormatter.ofPattern("d MMM uuuu"))
                    + ")";
        }
    }
}
