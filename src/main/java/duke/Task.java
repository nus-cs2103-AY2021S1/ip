package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task. A Task object has a description
 * and whether it is done.
 */
public class Task {
    String description;
    boolean isDone;
    String type = "";
    LocalDateTime deadline = null;

    /**
     * Creates a Task object with the specified Description.
     * @param description Description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getType() {
        return this.type;
    }

    public String getDeadline() {
        return this.deadline == null
                ? null
                : this.deadline.toString();
    }

    /**
     * @return This Task's deadline in the specified format.
     * @param f String representation of the format.
     */
    public String formatDeadline(String f) {
        return deadline.format(DateTimeFormatter.ofPattern(f));
    }

    /**
     * @return The Task that has been 'done'
     */
    public Task taskDone() {
        this.isDone = true;
        return this;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}