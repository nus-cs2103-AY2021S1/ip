// Task.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.task;

import ikura.util.Observable;

/**
 * An abstract class representing a Task. It contains a description (name) and records its current state
 * (done or not done).
 */
public abstract class Task extends Observable<Task> {

    private String title;
    private String description;

    private boolean done;

    /**
     * Constructs a new Task with the given description. It is set to uncompleted by default.
     *
     * @param title the task's title.
     */
    protected Task(String title) {
        this(title, "");
    }

    protected Task(String title, String description) {

        this.title = title;
        this.description = description;
        this.done = false;
    }

    /**
     * Gets the title of the task.
     *
     * @return the task's title.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Sets the title of the task to the new title, which can neither be null nor empty.
     *
     * @param title the new title
     */
    public void setTitle(String title) {
        assert title != null && !title.isEmpty();
        this.title = title;

        this.updateObservers();
    }

    /**
     * Gets the description of the task.
     *
     * @return the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description of the task, which can be empty but not null.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        assert description != null;
        this.description = description;

        this.updateObservers();
    }

    /**
     * Returns true if the task is completed, false otherwise.
     *
     * @return true if the task is completed, false otherwise.
     */
    public boolean isDone() {
        return this.done;
    }

    /**
     * Mark the task as completed. This should not be called on a task that is already
     * marked as done.
     */
    public void markAsDone() {

        assert !this.done;
        this.done = true;

        this.updateObservers();
    }

    /**
     * Mark the task as not completed. This should only be called on tasks that are already
     * completed.
     */
    public void markAsNotDone() {
        assert this.done;
        this.done = false;

        this.updateObservers();
    }

    /**
     * Check if the task has an associated date.
     *
     * @return true if the task has a date, false otherwise.
     */
    public abstract boolean hasDate();

    @Override
    public String toString() {
        return String.format("[%s] %s", this.done ? "\u2713" : "\u2718", this.title);
    }
}
