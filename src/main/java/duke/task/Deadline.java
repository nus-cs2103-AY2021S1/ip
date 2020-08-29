package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate deadline;
    private LocalTime time;

    /**
     * Constructs deadline object with
     * description and date that is not done.
     * @param description Description of the task.
     * @param deadline    Deadline date of the task.
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
        this.time = null;
    }

    /**
     * Constructs deadline object with
     * description, date, and time that is not done.
     * @param description Description of the task.
     * @param deadline    Deadline date of the task.
     * @param time        Deadline time of the task.
     */
    public Deadline(String description, LocalDate deadline, LocalTime time) {
        super(description);
        this.deadline = deadline;
        this.time = time;
    }

    /**
     * Constructs deadline object with description and date.
     * Constructs a done object if isDone is true
     * and not done object otherwise.
     * @param description Description of the task.
     * @param isDone      Indicates whether a task is done or not.
     * @param deadline    Deadline date of the task.
     */
    public Deadline(String description, Boolean isDone, LocalDate deadline) {
        super(description, isDone);
        this.deadline = deadline;
        this.time = null;
    }

    /**
     * Constructs deadline object with description, date, and time.
     * Constructs a done object if isDone is true
     * and not done object otherwise.
     * @param description Description of the task.
     * @param isDone      Indicates whether a task is done or not.
     * @param deadline    Deadline date of the task.
     * @param time        Deadline time of the task.
     */
    public Deadline(String description, Boolean isDone, LocalDate deadline, LocalTime time) {
        super(description, isDone);
        this.deadline = deadline;
        this.time = time;
    }

    /**
     * Returns D to mark this as a deadline object.
     * @return D in string.
     */
    public String getType() {
        return "D";
    }

    /**
     * Returns the date of this deadline.
     * @return Deadline date in LocalDate.
     */
    public LocalDate getDeadline() {
        return this.deadline;
    }

    /**
     * Returns the time of this deadline if it exists.
     * @return Deadline time in LocalTime.
     */
    public LocalTime getTime() {
        return this.time;
    }

    /**
     * Returns the string representation of this deadline object.
     * @return String object of this deadline.
     */
    @Override
    public String toString() {

        return this.time != null
            ? "[" + this.getType() + "]" + this.getStatusIcon() + " "
            + this.description + " (by:" + this.deadline.format(
            DateTimeFormatter.ofPattern("MMM d yyyy"))
            + " " + this.time.format(DateTimeFormatter.ISO_LOCAL_TIME) + ")"
            : "[" + this.getType() + "]" + this.getStatusIcon() + " "
            + this.description + " (by:" + this.deadline.format(
            DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Mark this deadline as done.
     * @return Done version of this deadline task.
     */
    @Override
    public Deadline markAsDone() {
        //int index = taskNum - 1;
        if (!this.isDone) {
            Deadline newTask = new Deadline(this.getDescription(), true, this.deadline, this.time);
            return newTask;
        }
        return this;
    }

}
