package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a task, which can be a todo, deadline or event.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;
    private final TaskPriority priority;
    private final List<String> tags;

    /**
     * The task constructor.
     *
     * @param description The description of the task.
     * @param isDone The boolean keeping track of whether the task is done.
     * @param priority Priority of task.
     * @param tags List of tags.
     */

    public Task(String description, boolean isDone, TaskPriority priority, List<String> tags) {
        assert(!description.isBlank()) : "Task description should not be empty";
        this.description = description;
        this.isDone = isDone;
        this.priority = priority;
        this.tags = new ArrayList<>(tags);
    }

    public abstract boolean isDue(LocalDate date);

    /**
     * Returns a boolean indicating if the task contains the given keyword.
     *
     * @param keyword A keyword.
     * @return true if the task contains the given keyword.
     */
    public boolean hasKeyword(String keyword) {
        assert(!keyword.isBlank()) : "Keyword should not be blank";
        return this.description.contains(keyword);
    }

    /**
     * Returns a status icon corresponding to whether the task is done.
     *
     * @return a tick if the task is done, and a cross otherwise.
     */
    public String getStatusIcon() {
        return this.isDone ? "\u2713" : "\u2718";
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a string representation of the task for saving into a file.
     *
     * @return a string representation of the task.
     */
    public String toSaveData() {
        return (this.isDone ? 1 : 0) + " | " + this.description;
    }

    /**
     * Returns a string representation of the task for displaying.
     *
     * @return a string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description + " (" + this.priority + ") " + this.tags;
    }
}
