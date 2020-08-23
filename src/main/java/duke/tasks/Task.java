package duke.tasks;

import java.time.LocalDate;

/**
 * Represents a Task object that the user wants
 * to keep track of.
 */
public class Task {

    private String taskName;
    private boolean isDone;
    private boolean hasFormattedDate;
    protected LocalDate localDate;

    /**
     * Creates an instance of a Task object with the appropriate
     * task name.
     *
     * @param taskName Name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        hasFormattedDate = false;
    }

    /**
     * Creates an instance of a Task object with the appropriate
     * task name. Receives date that task is due as a LocalDate object.
     *
     * @param taskName Name of the task.
     * @param hasFormattedDate true if the task has a formatted date. Otherwise, false.
     * @param localDate LocalDate object representing task deadline.
     */
    public Task(String taskName, boolean hasFormattedDate, LocalDate localDate) {
        this.taskName = taskName;
        this.isDone = false;
        this.hasFormattedDate = true;
        this.localDate = localDate;
    }

    /**
     * Returns whether the task has a due date.
     *
     * @return true if task has a due date. Otherwise, false.
     */
    public boolean hasDate() {
        return hasFormattedDate;
    }

    /**
     * Returns the date of the task as a LocalDate object.
     *
     * @return Date of object as a LocalDate object. If no date is
     * present, returns null.
     */
    public LocalDate getDate() {
        return hasDate()
                ? localDate
                : null;
    }

    /**
     * Sets task as one.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Returns the prefix to task display.
     *
     * @return Prefix of task display.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[" + "\u2713" + "] " + taskName;
        } else {
            return "[" + "\u2717" + "] " + taskName;
        }
    }

    /**
     * Converts the task to a savable format.
     *
     * @return Savable format of task.
     */
    public String getSaveFormat() {
        String done = isDone ? "1" : "0";
        return done + " | " + taskName;
    }
}
