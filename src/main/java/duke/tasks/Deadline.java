package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task saved by the user.
 */
public class Deadline extends Task {

    private String deadlineDate;

    /**
     * Creates an instance of a Deadline object with the appropriate
     * deadline name and date of deadline given in non standard format.
     *
     * @param deadlineName Description of task.
     * @param deadlineDate Date of deadline.
     */
    public Deadline(String deadlineName, String deadlineDate) {
        super(deadlineName);
        this.deadlineDate = deadlineDate;
    }

    /**
     * Creates an instance of a Deadline object with the appropriate
     * deadline name and date of deadline given in standard format.
     *
     * @param deadlineName Description of task.
     * @param localDate Date of deadline as a LocalDate object.
     */
    public Deadline(String deadlineName, LocalDate localDate) {
        super(deadlineName, true, localDate);
    }

    /**
     * Returns description of deadline.
     *
     * @return Description of deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + (super.hasDate() ? localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : deadlineDate) + ")";
    }

    /**
     * Converts deadline to a savable format.
     *
     * @return Savable format of deadline.
     */
    @Override
    public String getSaveFormat() {
        return "D" + " | " + super.getSaveFormat() + " | " + (super.hasDate() ? localDate : deadlineDate);
    }
}
