package duke.task;

import java.util.Objects;

/**
 * Represents a generic task, initializes to not done.
 */
public class Task {
    protected final String description;
    protected String time;
    protected TaskStatus status;
    protected TaskType type;

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
        this.time = null;
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
            ? "\u2714"
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
    public String printSaveFormat() {
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

    /**
     * Returns a deep copy of the current task to be retrieved later when undo is called.
     *
     * @return Deep copy of the given task.
     */
    public Task deepCopy() {
        if (type == TaskType.DEADLINE) {
            return Deadline.deepCopyDeadline(this);
        } else if (type == TaskType.EVENT) {
            return Event.deepCopyEvent(this);
        } else if (type == TaskType.TODO) {
            return Todo.deepCopyTodo(this);
        } else {
            return this;
        }
    }
}
