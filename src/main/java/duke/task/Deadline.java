package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Deadline class define rules for Deadline object. Deadline is a subclass of Task class.
 *
 * @author Galvin Leow Wen Yuan
 * @author A0200204J
 * @version v1.0
 */
public class Deadline extends Task {

    private LocalDateTime by;

    /**
     * Class constructor.
     * Initialises event class with description, isDone and time.
     *
     * @param description description of the deadline task.
     * @param by          date and time of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns date and time of the deadline object.
     *
     * @return LocalDateTime of the deadline object
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Update by time for task.
     *
     * @param by input date time for to update existing task.
     */
    public void setBy(LocalDateTime by) {
        this.by = by;
    }

    /**
     * Checks if two object are equal.
     * If equal true, else false.
     *
     * @param obj object to be compare.
     * @return true is same, false if differento
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        Deadline deadline = (Deadline) obj;
        return Objects.equals(by, deadline.by);
    }

    /**
     * Returns summary to the deadline object.
     *
     * @return string to the deadline object.
     */
    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, dd/MMM/yyyy HHmm");
        return String.format("[D]%s (by: %s)", super.toString(), by.format(dtf));
    }
}
