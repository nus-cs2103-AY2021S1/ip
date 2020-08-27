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

    public LocalDateTime by;

    /**
     * Class constructor.
     * Initialise event class with description, isDone and time.
     *
     * @param description description of the deadline task.
     * @param by          date and time of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Check if two object are equal.
     * If equal true, else false.
     *
     * @param o object to be compare.
     * @return true is same, false if different
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Deadline deadline = (Deadline) o;
        return Objects.equals(by, deadline.by);
    }

    /**
     * Summary to the deadline object.
     *
     * @return string to the deadline object.
     */
    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, dd/MMM/yyyy HHmm");
        return String.format("[D]%s (by: %s)", super.toString(), by.format(dtf));
    }
}
