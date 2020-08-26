// Task.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.task;

/**
 * An abstract class representing a Task. It contains a description (name) and records its current state
 * (done or not done).
 */
public abstract class Task {

    private String name;
    private boolean done;

    /**
     * Constructs a new Task with the given description. It is set to uncompleted by default.
     *
     * @param name the task's description.
     */
    protected Task(String name) {

        this.name = name;
        this.done = false;
    }

    /**
     * Gets the description of the task.
     *
     * @return name the task's description.
     */
    public String getName() {
        return this.name;
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
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.done ? "\u2713" : "\u2718", this.name);
    }
}
