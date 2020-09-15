package viscount.task;

import java.time.LocalDateTime;

import viscount.Parser;

/**
 * Represents a deadline, a type of task.
 */
public class Deadline extends Task {
    private static final String STRING_FORMAT = "[D][%s] %s (by: %s)";
    private static final String TASK_DATA_FORMAT = "%s|%d|%s|%s";

    /**
     * Deadlines have an additional due date field.
     */
    private LocalDateTime dueDate;

    /**
     * Initialises a new deadline task.
     *
     * @param description Description of the deadline created.
     * @param isDone Represents if the deadline is done.
     * @param dueDate Due date of the deadline.
     */
    public Deadline(String description, boolean isDone, LocalDateTime dueDate) {
        super(TaskType.DEADLINE, description, isDone);
        this.dueDate = dueDate;
    }

    /**
     * Returns whether the task has a date time field.
     *
     * @return True as deadlines have a date time field.
     */
    @Override
    public boolean hasDateTime() {
        return true;
    }

    /**
     * Gets the due date of the deadline.
     *
     * @return Due date of the deadline.
     */
    @Override
    public LocalDateTime getDateTime() {
        return dueDate;
    }

    /**
     * Sets the due date of the dateline.
     *
     * @param newDateTime New due date of the deadline.
     */
    @Override
    public void setDateTime(LocalDateTime newDateTime) {
        this.dueDate = newDateTime;
    }

    /**
     * Gives a task data representation of the task in String format.
     *
     * @return Task data representation of the task.
     */
    @Override
    public String toTaskData() {
        return String.format(Deadline.TASK_DATA_FORMAT, taskType.name(), isDone ? 1 : 0, description,
                dueDate.format(Parser.TASK_DATA_DATE_TIME_FORMATTER));
    }

    /**
     * Gives a displayable string representation of the task.
     *
     * @return Displayable string representation of the task.
     */
    @Override
    public String toString() {
        return String.format(Deadline.STRING_FORMAT, getStatusIcon(), description,
                dueDate.format(Parser.OUTPUT_DATE_TIME_FORMATTER));
    }

    /**
     * Compares this task with another object for equality.
     *
     * @param o Object compared.
     * @return True if this task is equal to the object, and false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Deadline deadline = (Deadline) o;
        boolean hasSameDescription = this.description.equals(deadline.description);
        boolean hasSameDone = this.isDone == deadline.isDone;
        boolean hasSameDueDate = this.dueDate.isEqual(deadline.dueDate);
        return hasSameDescription && hasSameDone && hasSameDueDate;
    }
}
