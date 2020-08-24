import java.time.LocalDate;

/**
 * Class for a tasks with a deadline
 */
public class Deadline extends Task {

    // Attributes
    protected LocalDate deadline;

    // Constructor

    /**
     * Creates a new task with deadline
     * @param description description of task
     * @param deadline deadline of the task in yyyy-mm-dd format
     * @throws EmptyBodyException
     */
    public Deadline(String description, LocalDate deadline) throws EmptyBodyException {
        super(description);
        this.deadline = deadline;
    }

    // String Representation
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}