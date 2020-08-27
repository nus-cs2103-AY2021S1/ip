import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Inherits from the Task class. Represents a task with a deadline.
 * Deadline object stores task description, done status and deadline of task.
 * A Deadline object is marked as not done by default.
 * A Deadline object is able to be set as done by calling setDone() method from the parent class.
 */
public class Deadline extends Task {
    protected LocalDate byDate;
    protected LocalTime byTime;

    /**
     * Deadline object constructor overrides Task constructor.
     * Takes in a deadline in addition to the task description
     * and parses into LocalDate and LocalTime format.
     * @param description description of task
     * @param by deadline of task
     */
    public Deadline(String description, String by) {
        super(description);
        this.byDate = LocalDate.parse(by.split(" ")[0]);
        this.byTime = LocalTime.parse(by.split(" ")[1]);
    }

    /**
     * Returns Deadline object as a string.
     * This method takes no parameters and returns the Deadline object as a string
     * in the form "[D][<Done Status>] <Deadline Description> (by: <Date> <Time>)".
     * This method overrides the method from parent class.
     * @return String this returns the deadline object as a string
     */
    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mma");
        return "[D]" + super.toString() + " (by: "
                + dateFormatter.format(byDate) + " "
                + timeFormatter.format(byTime) + ")";
    }

    /**
     * Returns Deadline object as a string.
     * This method takes no parameters and returns the Deadline object as a string
     * as a string format suitable for being parsed into a Deadline object.
     * String is in the form "[D][<Done Status>] <Deadline Description> (by: <Date> <Time>)".
     * This method overrides the method from parent class.
     * @return String this returns the deadline object as a string
     */
    @Override
    public String toStringFileFormat() {
        return "[D]" + super.toStringFileFormat() + " (by: " + byDate + " " + byTime + ")";
    }
}
