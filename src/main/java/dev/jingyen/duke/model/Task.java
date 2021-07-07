package dev.jingyen.duke.model;

import java.util.Objects;

import dev.jingyen.duke.storage.Storable;

/**
 * The dev.jingyen.duke.model.Task represents something that can be done.
 *
 * @author jingyenloh
 */
public abstract class Task implements Storable {
    protected boolean isDone;
    protected String taskName;

    protected Task(String taskName) {
        this(false, taskName);
    }

    protected Task(boolean isDone, String taskName) {
        assert taskName != null && !taskName.isBlank();
        this.isDone = isDone;
        this.taskName = taskName;
    }

    /**
     * Marks a task as done.
     */
    public void markDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "✓" : "✘", taskName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Task task = (Task) o;

        if (isDone != task.isDone) {
            return false;
        }
        return Objects.equals(taskName, task.taskName);
    }

    /**
     * Checks if the Task name matches a search term.
     *
     * @param term the search term
     * @return true if the task contains the search term, otherwise false
     */
    public boolean contains(String term) {
        return taskName.contains(term);
    }
}
