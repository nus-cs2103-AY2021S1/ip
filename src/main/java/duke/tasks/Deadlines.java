package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class that represents the a task that has a deadline.
 */
public class Deadlines extends Task {
    private LocalDateTime by;

    /**
     * Constructs the deadline class.
     *
     * @param description the string of description of the deadline task.
     * @param cutTime the LocalDateTime object that represents the deadline of the task.
     */
    public Deadlines(String description, LocalDateTime cutTime) {
        super(description);
        this.by = cutTime;
    }


    /**
     * Returns the deadline datetime of the task.
     *
     * @return the LocalDateTime object of deadline.
     */
    public LocalDateTime getBy() {
        return by;
    }


    /**
     * Returns the deadline date of the task.
     *
     * @return the localDate object of the task deadline.
     */
    public LocalDate getDate() {
        return by.toLocalDate();
    }


    /**
     * Returns the string representation of the deadline task.
     *
     * @return the string representation of deadline task, including status icon, description,
     *         and description.
     */
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH : mm")) + ")";
    }
}
