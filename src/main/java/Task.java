import java.time.LocalDateTime;

public class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task object to represent a task.
     *
     * @param description Description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the Task.
     *
     * @return The status icon of the Task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Updates the Task as done.
     *
     * @return true when the Task is marked as done.
     */
    public boolean markAsDone() {
        this.isDone = true;
        return true;
    }

    /**
     * Returns the task type of the Task.
     *
     * @return The task type of the Task.
     */
    public String getTaskType() {
        if (this instanceof Todo) {
            return "T";
        } else if (this instanceof Deadline) {
            return "D";
        } else {
            return "E";
        }
    }

    /**
     * Returns the description of the Task.
     *
     * @return The description of the Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a boolean indicating whether the Task is done.
     *
     * @return the boolean indicating whether the Task is done.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns a boolean indicating whether the task has a time attached to it.
     *
     * @return the boolean indicating whether the task has a time attached to it.
     */
    public boolean isTimeIncluded() {
        return !(this instanceof Todo);
    }

    /**
     * Returns the LocalDateTime object associated with the task.
     *
     * @return the LocalDateTime object associated with the task.
     */
    public LocalDateTime getDateTime() {
        if (this instanceof Deadline) {
            return ((Deadline) this).dateTime;
        } else {
            // If this is instance of Event
            return ((Event) this).startTime;
        }
    }

    @Override
    public int compareTo(Task other) {
        if (!this.isDone && other.isDone) {
            return -1;
        } else if (this.isDone && !other.isDone) {
            return 1;
        } else {
            // When both is done or both not done
            if (!this.isTimeIncluded() && !other.isTimeIncluded()) {
                return 0;
            } else if (this.isTimeIncluded() && !other.isTimeIncluded()) {
                return -1;
            } else if (!this.isTimeIncluded() && other.isTimeIncluded()) {
                return 1;
            } else {
                int result = this.getDateTime().isAfter(other.getDateTime()) ? 1 : -1;;
                return result;
            }
        }
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
