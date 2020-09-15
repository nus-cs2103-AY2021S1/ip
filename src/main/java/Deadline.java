import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task called Deadline.
 * A deadline has a attribute "dueDateAsString" or "dueDateAsDate", which is the time the deadline is due.
 * The attribute dueDate can be represented by a String, or a date object.
 */
public class Deadline extends Task {
    protected String dueDateAsString;
    protected LocalDate dueDateAsDate;

    Deadline(String taskDescription, String date) {
        assert(date.length() > 0);
        assert(taskDescription.length() > 0);
        this.taskDescription = taskDescription;
        this.isCompleted = false;
        this.dueDateAsString = date;
        this.dueDateAsDate = null;
    }

    Deadline(String taskDescription, LocalDate date) {
        assert(date != null);
        assert(taskDescription.length() > 0);
        this.taskDescription = taskDescription;
        this.isCompleted = false;
        this.dueDateAsDate = date;
        this.dueDateAsString = null;
    }

    /**
     * Return the due date of the deadline.
     *
     * @return Due date of deadline.
     */
    public String getDate() {
        if (dueDateAsString != null) {
            assert(dueDateAsString.length() > 0);
            return dueDateAsString;
        } else {
            assert(dueDateAsDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")).length() > 0);
            return dueDateAsDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
    }

    /**
     * Return the type of Task.
     *
     * @return Type of task.
     */
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        if (dueDateAsDate != null) {
            return "[D]" + super.toString() + " (by: "
                    + dueDateAsDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: "
                    + dueDateAsString + ")";
        }
    }
}
