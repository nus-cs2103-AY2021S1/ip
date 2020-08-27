package duke.task;

import java.util.Objects;

/**
 * Represents a generic DukeTask.
 * It contains a <code>Strign</code> description describing the task,
 * a <code>boolean</code> isDone to denote if the task is completed.
 */
public abstract class DukeTask {
    private final String description;
    private boolean isDone;

    public DukeTask(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a <code>String</code> representation of isDone.
     * If the task is completed, it returns a tick denoted by "\u2713".
     * Else, it returns a cross denoted by "\u2718".
     *
     * @return String status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Returns a <code>String</code> representing the description.
     *
     * @return String description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the DukeTask as completed by setting <code>boolean</code> isDone to <code>true</code>.
     *
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a <code>boolean</code> representing the status of the task.
     *
     * @return boolean isDone.
     */
    public boolean getDoneStatus() {
        return isDone;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DukeTask dukeTask = (DukeTask) o;
        return isDone == dukeTask.isDone &&
                description.equals(dukeTask.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, isDone);
    }
}