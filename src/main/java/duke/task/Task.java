package duke.task;

import java.time.LocalDate;

/**
 * Encapsulates methods and information relating to a task.
 */
public abstract class Task {

    /** Description of the task. */
    private final String description;
    /** Boolean describing whether the task has been completed. */
    private boolean isDone;
    /** Tag attached to a task. */
    private String tag;

    /**
     * Creates and initialises a new task object.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.tag = "";
        this.isDone = false;
    }

    /**
     * Returns a status icon to indicate the completion status of this task.
     *
     * @return Tick symbol if the task is completed, else a cross symbol is returned.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marks this task object as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Converts the task object into a string for storage in a file.
     *
     * @return String containing the relevant information of this task object to be saved in a file.
     */
    public abstract String convertTaskToFileString();

    /**
     * Attaches a tag to the task object.
     *
     * @param tag Task tag to be attached.
     */
    public void setTaskTag(String tag) {
        this.tag = tag;
    }

    /**
     * Returns the date of this task.
     *
     * @return LocalDate object that stores the date of the task.
     */
    public abstract LocalDate getDate();

    /**
     * Returns a boolean to indicate whether the task has been completed.
     *
     * @return True if the task is completed, false otherwise.
     */
    public boolean hasBeenCompleted() {
        return this.isDone;
    }

    /**
     * Returns the description of this task object.
     *
     * @return String containing the description of this task.
     */
    public String getDescription() {
        assert !this.description.isBlank() : "task description cannot be empty";
        return this.description;
    }

    /**
     * Returns the tag of this task object.
     *
     * @return String containing the tag of this task.
     */
    public String getTaskTag() {
        return this.tag;
    }

    /**
     * Converts the task object into a string to be displayed.
     *
     * @return String representation of this task object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]"
                + (this.tag.isBlank() ? " " : "[#" + this.tag + "] ") + this.description;
    }
}
