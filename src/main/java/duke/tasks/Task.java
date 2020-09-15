package duke.tasks;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable {
    protected String description;
    protected boolean isDone;
    protected LocalDate localDate;

    /**
     * @param description Task's description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.localDate = null;
    }

    /**
     * @param description Task's description.
     * @param localDate Date associated with the task.
     */
    public Task(String description, LocalDate localDate) {
        this.description = description;
        this.isDone = false;
        this.localDate = localDate;
    }

    /**
     * Getter method to retrieve the status of the task.
     *
     * @return String representation of the status of the Task.
     */
    public String getStatusIcon() {
        return (isDone ? "[\u2713] " : "[\u2718] ");
    }

    /**
     * @return Description of the Task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return Date of the Task.
     */
    public LocalDate getLocalDate() {
        return localDate;
    }

    /**
     * @return Type of the Task.
     */
    public TaskType getTaskType() {
        return null;
    }

    /**
     * Mark a task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done: " + "\n" + "[\u2713]"
                + this.description);
    }

    /**
     * Returns the String representation of this task to be displayed to the user.
     *
     * @return The String representation of this task to be displayed to the user.
     */
    @Override
    public String toString() {
        return description;
    }

    /**
     * Enum to represents the different task types.
     */
    public enum TaskType {
        Deadline,
        ToDo,
        Event
    }
}
