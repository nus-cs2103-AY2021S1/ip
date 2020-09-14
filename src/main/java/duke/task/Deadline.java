package duke.task;

import java.time.LocalDateTime;

/**
 * Represents a Task that has a deadline.
 */
public class Deadline extends Task {

    private LocalDateTime dueDate;

    /**
     * Constructs a new Deadline with the given description and due date.
     *
     * @param description description of the Deadline
     * @param dueDate due date of the Deadline
     */
    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        super.symbol = 'D';
        this.dueDate = dueDate;
    }

    /**
     * Constructs a new Deadline with the given description, due date and completion status.
     *
     * @param description description of the Deadline
     * @param dueDate due date of the Deadline
     * @param isCompleted whether the Deadline Task has been completed
     */
    public Deadline(String description, LocalDateTime dueDate, boolean isCompleted) {
        super(description, isCompleted);
        super.symbol = 'D';
        this.dueDate = dueDate;
    }

    @Override
    public Deadline markCompleted() {
        return new Deadline(description, dueDate, true);
    }

    @Override
    public String getStorageString() {
        String baseString = super.getStorageString();
        return String.format("%s | %s", baseString, dueDate);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", symbol, super.toString(), dueDate);
    }
}
