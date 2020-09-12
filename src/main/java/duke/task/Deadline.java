package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates methods and information that relate to a deadline task.
 */
public class Deadline extends Task {

    /** Due date of the deadline task. */
    private final LocalDate deadlineDate;
    /** Time in which the deadline task is due. */
    private final LocalTime deadlineTime;

    /**
     * Creates and initialises a new Deadline object.
     *
     * @param description Description of the deadline task.
     * @param deadlineDate LocalDate object that stores the due date of the deadline task.
     * @param deadlineTime LocalTime object that stores the time the deadline task is due.
     */
    public Deadline(String description, LocalDate deadlineDate, LocalTime deadlineTime) {
        super(description);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    /**
     * Returns the due date of the deadline task.
     *
     * @return LocalDate object that stores the due date of the deadline task.
     */
    @Override
    public LocalDate getDate() {
        return this.deadlineDate;
    }

    /**
     * Converts the deadline object into a string for storage in a file.
     *
     * @return String containing the relevant information of this deadline object to be saved in a file.
     */
    @Override
    public String convertTaskToFileString() {
        return "D | " + (hasBeenCompleted() ? "1 | " : "0 | ") + getDescription() + " | " + getTaskTag()
                + " | " + this.deadlineDate.toString() + " " + this.deadlineTime.toString();
    }

    /**
     * Converts the deadline object into a string to be displayed.
     *
     * @return String representation of this deadline object.
     */
    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EE, MMM dd yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        String formattedDeadlineTime = this.deadlineDate.format(dateFormatter)
                + ", " + this.deadlineTime.format(timeFormatter);
        return "[D]" + super.toString() + " (by: " + formattedDeadlineTime + ")";
    }
}
