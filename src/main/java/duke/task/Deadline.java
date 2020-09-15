package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline to be completed before a due date.
 */
public class Deadline extends Task {
    /** The date the deadline is due */
    protected LocalDate dueDate;

    /**
     * Creates a new Deadline with the specified description and due date.
     *
     * @param description the description of the deadline.
     * @param dueDate the due date.
     */
    public Deadline(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Loads previous deadline details and creates a new instance of the deadline.
     *
     * @param deadlineDetails the details of the deadline in 'saved' format.
     * @return a new instance of the deadline.
     */
    public static Deadline load(String deadlineDetails) {
        // Split into type of task, status, description and due date
        String[] splitDeadlineDetails = deadlineDetails.split(" \\| ", 4);

        String status = splitDeadlineDetails[1];
        String description = splitDeadlineDetails[2];
        LocalDate dueDate = LocalDate.parse(splitDeadlineDetails[3]);
        Deadline deadline = new Deadline(description, dueDate);

        if (status.equals("true")) {
            deadline.markAsDone();
        }
        return deadline;
    }

    /**
     * Returns the description and status of a deadline in a particular string format.
     * This format is used for saving deadlines, i.e. 'saved' format.
     *
     * @return a string representation of the deadline in a 'saved' format.
     */
    @Override
    public String saveAs() {
        return "D | " + super.saveAs() + " | " + dueDate;
    }

    /**
     * Checks if this Deadline is equal to the specified object.
     *
     * @param obj the object to check.
     * @return true if this is equal to the specified object or false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Deadline) {
            Deadline deadline = (Deadline) obj;

            return this.description.equals(deadline.description)
                    && this.dueDate.equals(deadline.dueDate);
        } else {
            return false;
        }
    }

    /**
     * Returns a string representation of the deadline.
     *
     * @return a string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate.format(
                DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
