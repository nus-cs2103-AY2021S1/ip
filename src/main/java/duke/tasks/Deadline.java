package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A class that represents the a task that has a deadline.
 */
public class Deadline extends Task {
    private LocalDateTime by;

    /**
     * Constructs the deadline class.
     *
     * @param description the string of description of the deadline task.
     * @param cutTime the LocalDateTime object that represents the deadline of the task.
     */
    public Deadline(String description, LocalDateTime cutTime) {
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
     * Sets the deadline date of the task.
     *
     * @param by the localDate object of the task deadline.
     */
    public void setBy(LocalDateTime by) {
        this.by = by;
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return the string representation of deadline task, including status icon, description,
     *         and description.
     */
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MM d yyyy HH : mm")) + ")";
    }
}
