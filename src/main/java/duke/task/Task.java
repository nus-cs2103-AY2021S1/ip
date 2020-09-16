package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a task, which can be a todo, deadline or event.
 */
public abstract class Task {
    private final String description;
    private final TaskPriority priority;
    private final List<String> tags;
    private boolean isDone;

    /**
     * The task constructor.
     *
     * @param description The description of the task.
     * @param priority Priority of task.
     * @param tags List of tags.
     * @param isDone The boolean keeping track of whether the task is done.
     */

    public Task(String description, TaskPriority priority, List<String> tags, boolean isDone) {
        assert(!description.isBlank()) : "Task description should not be empty";
        this.description = description;
        this.priority = priority;
        this.tags = new ArrayList<>(tags);
        this.isDone = isDone;
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

    public boolean hasPriority(TaskPriority priority) {
        return this.priority.equals(priority);
    }

    /**
     * Return a boolean indicating if the task contains all the tags in the given list.
     * @param tags A list of tags.
     * @return true if the task contains all the given tags.
     */
    public boolean hasTags(List<String> tags) {
        if (tags.isEmpty()) {
            return this.tags.isEmpty();
        }

        return this.tags.containsAll(tags);
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
        return this.priority + " | " + this.tags + " | " + (this.isDone ? 1 : 0) + " | " + this.description;
    }

    /**
     * Returns a string representation of the task for displaying.
     *
     * @return a string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.priority.toSymbol() + " " + this.description + " " + this.tags;
    }
}
