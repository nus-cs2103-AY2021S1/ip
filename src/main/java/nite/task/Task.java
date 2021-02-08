package nite.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task created by the user.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected final DateTimeFormatter inputDateTimePattern =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected final DateTimeFormatter displayDateTimePattern =
            DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

    /**
     * Default constructor for a Task.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        assert !description.isEmpty() : "Description should not be empty.";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the symbolic representation of the completion status of the task.
     *
     * @return Tick if task is completed, cross if task is not completed.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the String representation of a Task.
     *
     * @return String representation of a Task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * Returns the text representation of a Task to be saved in a text file.
     * Default Method to be overridden by classes which inherit Task.
     *
     * @return Text representation of Task.
     */
    public abstract String toData();

    /**
     * Checks if a task contains the given keyword in its description.
     *
     * @param keyword Word to find in task description.
     * @return True if description contains keyword, false otherwise.
     */
    public boolean hasKeyword(String keyword) {
        assert !keyword.isEmpty() : "Keyword should not be empty.";
        return description.contains(keyword);
    }

    /**
     * Returns the type of the Task.
     *
     * @return Type of Task.
     */
    public abstract String typeOfTask();

    public LocalDateTime getTime() {
        return null;
    }
}
