package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import duke.command.Command;

public abstract class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs task.
     *
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs task.
     *
     * @param description Task description.
     * @param isDone      Done indicator.
     */
    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status of the task.
     *
     * @return A tick sign if the task is done and a cross otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "[" + "\u2713" + "]" : "[" + "\u2718" + "]");
    }

    /**
     * Returns the type of the task.
     *
     * @return E for event, T for todo, D for deadline.
     */
    public abstract String getType();

    /**
     * Returns the task decription.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get status of the task.
     *
     * @return True if the task is done and false otherwise.
     */

    public Boolean getStatus() {
        return isDone;
    }

    /**
     * Mark this task as done.
     *
     * @return Done version of the old task.
     */
    public abstract Task markAsDone();

    public LocalDate getTaskDate() {
        if(this instanceof Todo) {
            return LocalDate.MAX;
        } else if (this instanceof Event) {
            return ((Event) this).getEventDate();
        } else if (this instanceof Deadline) {
            return ((Deadline) this).getDeadline();
        } else {
            return null;
        }
    }

    public LocalTime getTaskTime() {
        if (this instanceof Todo) {
            return LocalTime.MAX;
        } else if (this instanceof Event) {
            LocalTime eventTime = ((Event) this).getStartTime();
            return eventTime == null
                ? LocalTime.MAX
                : eventTime;
        } else if (this instanceof Deadline) {
            LocalTime deadlineTime = ((Deadline) this).getDeadlineTime();
            return deadlineTime == null
                ? LocalTime.MAX
                : deadlineTime;

        } else {
            return null;
        }
    }

    @Override
    public int compareTo(Task other) {
        if(this.getTaskDate().compareTo(other.getTaskDate()) > 0) {
            if (this.getTaskTime().compareTo(other.getTaskTime()) > 0) {
                if (this.getDescription().compareTo(other.getDescription()) > 0) {
                    return 1;
                } else if (this.getDescription().compareTo(other.getDescription()) == 0) {
                    return 0;
                } else {
                    return -1;
                }
            } else if (this.getTaskTime().compareTo(other.getTaskTime()) == 0) {
                return 0;
            } else {
                return -1;
            }
        } else if (this.getTaskDate().compareTo(other.getTaskDate()) == 0) {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * Returns string representation of this task.
     *
     * @return String object of this task.
     */
    @Override
    public abstract String toString();

}
