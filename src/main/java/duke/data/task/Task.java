package duke.data.task;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a Task in the task list.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy hh:mm a");

    /**
     * Constructs a {@code Task}.
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a {@code Task}.
     * @param isDone True if task is completed, false otherwise.
     * @param description The task description.
     */
    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of the task. "O" for completed task, "X" for uncompleted task.
     * @return The status icon of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Retrieves a listing of every word in the description, in order.
     */
    public List<String> getWordsInDescription() {
        return Arrays.asList(description.split("\\s+"));
    }

    public String getStatusIcon() {
        return (isDone ? "O" : "X"); //return O or X symbols
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public boolean taskIsDone() {
        return this.isDone;
    }

    /**
     * Returns the string of the task to be added into the local storage file.
     * @return The string of the task in the local storage file
     */
    public abstract String fileFormat();

    @Override
    public String toString() {
        return String.format("[%1$s] %2$s", this.getStatusIcon(), this.description);
    }

}

