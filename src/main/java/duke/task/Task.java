package duke.task;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Encapsulates a task to be completed by the user.
 * Contains a string description and a boolean representation of whether the task has been completed.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDate doneDate;

    /**
     * Creates a task with the given description.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
        doneDate = null;
    }

    /**
     * Creates a task that has been completed on the given doneDate with the given description.
     * @param description Description of the task.
     * @param doneDate Date of completion.
     */
    public Task(String description, LocalDate doneDate) {
        this.description = description;
        isDone = true;
        this.doneDate = doneDate;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marks a task as done by setting the boolean isDone to true.
     */
    public void markAsDone() {
        isDone = true;
        doneDate = LocalDate.now();
    }

    public boolean contains(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Returns the string representation of the task that is recognised by the Storage class.
     * It is used to represent a task in the database.
     * @return String representation of the task.
     */
    public String toData() {
        if (isDone) {
            return 1 + " | " + description + " | " + doneDate;
        } else {
            return 0 + " | " + description;
        }
    }

    /**
     * Returns true if the task has been completed within 7 days before the given date.
     * @param date Date of reference.
     * @return True if the task has been completed within 7 days before the given date.
     */
    public boolean isDoneThisWeek(LocalDate date) {
        if (isDone) {
            long daysApart = ChronoUnit.DAYS.between(doneDate, date);
            if (daysApart <= 7) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String statusIcon = getStatusIcon();
        return String.format("[%s] %s", statusIcon, description);
    }
}
