package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task. A Task object has a description
 * and whether it is done.
 */
public class Task {
    protected String type = "";
    protected LocalDateTime deadline = null;
    private String description;
    private boolean isDone;

    /**
     * Creates a Task object with the specified Description.
     * @param description Description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String desc, boolean isDone) {
        this.description = desc;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "D" : "X"); //return tick or X symbols
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


    public String formatDeadline(String s) {
        return deadline.format(DateTimeFormatter.ofPattern(s));
    }

    /**
     * @return The Task that has been 'done'
     */
    public Task taskDone() {
        return new Task(this.description, this.isDone);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
