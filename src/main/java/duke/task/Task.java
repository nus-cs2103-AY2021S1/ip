package duke.task;

import java.time.LocalDate;

// Parent class for all types of tasks that can be created by the user.
public abstract class Task {
    protected String description;
    protected boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns an icon that represents the task status.
     *
     * @return String ✘ if not done and ✓ if done
     */
    public String getStatusIcon() {
        return isDone ? "\u2713" : "\u2718";
    }

    /**
     * Converts the task into a string format that will be stored in the save file.
     *
     * @return String storage information of task
     */
    public abstract String toSaveString();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }

    /**
     * Returns true or false based on whether the task is due on the date provided.
     *
     * @param date LocalDate the date to check
     * @return boolean Whether the task is due on the specified date
     */
    public abstract boolean isDueOn(LocalDate date);
}
