import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task called Deadline.
 * A deadline has a attribute "dueDateAsString" or "dueDateAsDate", which is the time the deadline is due.
 * The attribute dueDate can be represented by a String, or a date object.
 */
public class Deadline extends Task{
    protected String dueDateAsString;
    protected LocalDate dueDateAsDate;

    Deadline(String taskDescription, String date) {
        this.taskDescription = taskDescription;
        this.isCompleted = false;
        this.dueDateAsString = date;
        this.dueDateAsDate = null;
    }

    Deadline(String taskDescription, LocalDate date) {
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
            return dueDateAsString;
        } else {
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
            return "[D]" + super.toString() + " (by: " + dueDateAsDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + dueDateAsString + ")";
        }
    }
}
