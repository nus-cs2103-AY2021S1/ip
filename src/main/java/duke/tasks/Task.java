package duke.tasks;

import java.time.LocalDate;

/** A generic class from which all task-related classes will inherit from. */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task associated with a description.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * @return A tick if the task has been marked as done and a cross otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDate getDate() {
        return null;
    }

    /**
     * @return A String representing the Task object, to be used when saving tasks to the storage file.
     */
    public String txtFileFormat() {
        return (this.isDone ? 1 : 0) + " ~/~ " + this.description;
    }

    /**
     * @return A String representing the Task object, to be used when printing the Task.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
