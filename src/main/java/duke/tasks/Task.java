package duke.tasks;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable {
    protected String description;
    protected boolean isDone;
    protected LocalDate localDate;

    /**
     * @param description task's description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.localDate = null;
    }

    /**
     * @param description task's description.
     * @param localDate   date associated with the task.
     */
    public Task(String description, LocalDate localDate) {
        this.description = description;
        this.isDone = false;
        this.localDate = localDate;
    }

    /**
     * Getter method to retrieve the status of the task.
     *
     * @return status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "[\u2713] " : "[\u2718] "); //return tick or X symbols
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public String getTaskType() {
        return "";
    }

    /**
     * Mark a task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done: " + "\n" + "[\u2713]"
                + this.description);
    }

    @Override
    public String toString() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }
}
