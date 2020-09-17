package sparrow.data.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline in the task list.
 */
public class Deadline extends Task {
    protected LocalDate dueDate;

    /**
     * Creates a Deadline.
     * @param description details of deadline
     * @param dueDate when deadline is due
     */
    public Deadline(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return String.format("[D]%s (by: %s)", super.toString(), this.dueDate.format(formatter));
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof Deadline
                && description.equals(((Deadline) other).description)
                && dueDate.equals(((Deadline) other).dueDate));
    }
}
