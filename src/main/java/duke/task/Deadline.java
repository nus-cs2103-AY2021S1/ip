package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a {@link Task} that has a deadline.
 */
public class Deadline extends Task {
    /**
     * The date on which the {@link Task} is due.
     */
    private LocalDate endDate;

    /**
     * Instantiates a new Deadline object.
     * The new Deadline is not completed by default.
     * @param description The description of the Deadline.
     * @param endDate The due date of the Deadline.
     */
    public Deadline(String description, LocalDate endDate) {
        super(description);
        this.endDate = endDate;
    }

    /**
     * Instantiates a new Deadline object based on completion status provided.
     * @param description The description of the Deadline.
     * @param endDate The due date of the Deadline.
     * @param completionStatus The completion status of the Deadline.
     */
    public Deadline(String description, LocalDate endDate, String completionStatus) {
        super(description, completionStatus);
        this.endDate = endDate;
    }

    /**
     * Overrides getType method in {@link Task}.
     * @return Type of Deadline.
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Overrides getDate method in {@link Task}.
     * @return The date of the Deadline.
     */
    @Override
    LocalDate getDate() {
        return endDate;
    }

    /**
     * Returns a String representation of a Deadline.
     * Overrides toString in {@link Task}.
     * @return A String representation of a Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + endDate.format(DateTimeFormatter.ofPattern("MMMM d yyyy")) + ")";
    }
}
