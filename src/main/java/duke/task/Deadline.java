package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline to be completed before a certain date.
 */
public class Deadline extends Task {
    protected LocalDate dueDate;

    /**
     * Creates a new Deadline with the specified description and due date.
     *
     * @param description the description of the deadline.
     * @param dueDate the due date.
     */
    public Deadline(String description, LocalDate dueDate) {
        super(description);
        this.dueDate = dueDate ;
    }

    /**
     * Loads previous deadline details and creates a new instance of the deadline.
     *
     * @param deadlineDetails the details of the deadline in 'saved' format.
     * @return a new instance of the deadline.
     */
    public static Deadline load(String deadlineDetails) {
        String[] splitDeadlineDetails = deadlineDetails.split(" \\| ", 4);
        Deadline deadline = new Deadline(splitDeadlineDetails[2], LocalDate.parse(splitDeadlineDetails[3]));
        if (splitDeadlineDetails[1].equals("true")) {
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