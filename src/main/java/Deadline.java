import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a deadline task. Each deadline object has a name, due date and time.
 */
public class Deadline extends Task {

    private final LocalDate dueDate;
    private final LocalTime time;

    /**
     * Public constructor.
     *
     * @param name    Name of task.
     * @param dueDate Due date.
     * @param time    Time of due.
     * @throws BlankTaskException If the name of task is blank.
     */
    public Deadline(String name, LocalDate dueDate, LocalTime time) throws BlankTaskException {
        super(name);
        this.dueDate = dueDate;
        this.time = time;
    }

    /**
     * Gets the due date.
     *
     * @return Due date.
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Gets the time due.
     *
     * @return Time due.
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Returns a String representation of the Deadline. Includes the task type, name, done status, date, and time (if
     * applicable).
     *
     * @return String representation of the Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate.format(DateTimeFormatter.ofPattern("EEE, d MMM yyyy"))
                + (time == null ? "" : ", " + time.format(DateTimeFormatter.ofPattern("h.mm a"))) + ")";
    }

    /**
     * Returns a list of attributes of the deadline. Includes the type, name, done status, date, and time (if
     * applicable).
     *
     * @return List of attributes of the deadline.
     */
    @Override
    public String[] attributeList() {
        return new String[] { "D", getName(), dueDate.toString(), time.toString(), String.valueOf(isDone()) };
    }

    /**
     * Compares this Deadline to another object. The result is true if and only if the other object is a deadline with
     * all the attributes being the same as the attributes of this deadline.
     *
     * @param o Other object.
     * @return True if the given object represents a Deadline equivalent to this deadline.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Deadline deadline = (Deadline) o;
        return Objects.equals(dueDate, deadline.dueDate) &&
                Objects.equals(time, deadline.time);
    }

    /**
     * Returns a hash code for this deadline. The hashcode is determined from the deadline's name, dueDate, and time.
     *
     * @return A hashcode for the deadline.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dueDate, time);
    }
}
