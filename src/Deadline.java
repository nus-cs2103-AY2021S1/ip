import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline class that represents a Task with a deadline. Extends from the Task class.
 */
public class Deadline extends Task {
    LocalDateTime deadline;

    /**
     * Constructor that creates a Deadline object that has a description of the
     * task, the date and the time of the deadline.
     * @param description a String representing the description of the task
     * @param deadline a LocalDateTime representing the date and the time of the deadline.
     * @throws DateTimeParseException
     */
    public Deadline(String description, String deadline) throws DateTimeParseException {
        super(description);
        this.deadline = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    /**
     * Constructor that creates a Deadline object that has a description of the
     * task, the date and the time of the deadline, and whether the task has been completed.
     * @param description a String representing the description of the task.
     * @param deadline a LocalDateTime representing the date and the time of the deadline.
     * @param isDone boolean representing whether the task has been completed.
     */
    public Deadline(String description, String deadline, Boolean isDone) {
        super(description, isDone);
        this.deadline = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + this.getIcon() + description + " (by: " +
                this.deadline.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))+ ")";
    }

    /**
     * Returns a String formatted to how a Deadline should be saved in the associated save file.
     * @return a formatted String to be written to the save file.
     */
    public String toSaveString() {
        return String.format("D | %s | %s | %s",
                super.doneString(), this.description, this.deadline.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")));
    }

}
