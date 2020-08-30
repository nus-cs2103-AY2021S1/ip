package duke.task;

import java.util.Objects;

/**
 * Represents a generic task, initializes to not done.
 */
public class Task {
    protected final String description;
    protected TaskStatus status;

    /**
     * Denotes whether task is done or not done.
     */
    public enum TaskStatus {
        DONE, NOTDONE
    }

    /**
     * Denotes specific type of task.
     */
    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    /**
     * Initializes with a description and sets task to not done by default.
     *
     * @param desc Description.
     */
    public Task(String desc) {
        this.status = TaskStatus.NOTDONE;
        this.description = desc;
    }

    /**
     * Retrieves task description.
     *
     * @return Task's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Represents done or not done in string.
     *
     * @return unicode character for tick/cross.
     */
    public String getStatusIcon() {
        return (this.status == TaskStatus.DONE
            // return tick or X symbols
            ? "\u2713"
            : "\u2718");
    }

    /**
     * Marks given task as done.
     */
    public void markAsDone() {
        this.status = TaskStatus.DONE;
    }

    /**
     * Queries whether task is completed.
     *
     * @return whether the task is done or not
     */
    public boolean isDone() {
        return this.status == TaskStatus.DONE;
    }

    /**
     * Converts the task to a string for saving.
     *
     * @return String representing a task in save file.
     */
    public String toSaveFormat() {
        return description;
    }

    /**
     * Converts the task to a string indicating type of task, done, description and time (if applicable).
     *
     * @return String representing task in user interface.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    /**
     * Compares task by description and status.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task task = (Task) o;
        return description.equals(task.description)
            && status == task.status;
    }
    @Override
    public int hashCode() {
        return Objects.hash(description, status);
    }
}
