// Task.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.task;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;

import ikura.util.Observable;

/**
 * An abstract class representing a Task. It contains a description (name) and records its current state
 * (done or not done).
 */
public abstract class Task implements Observable<Task> {

    private String title;
    private String description;

    private boolean done;

    private final List<Consumer<Task>> observers = new ArrayList<>();

    private static final String lipsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

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

        //! asdf
        this.description = lipsum;

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

    @Override
    public String toString() {
        return String.format("[%s] %s", this.done ? "\u2713" : "\u2718", this.title);
    }

    @Override
    public void addObserver(Consumer<Task> observer) {
        this.observers.add(observer);
    }

    private void updateObservers() {
        for (var observer : this.observers) {
            observer.accept(this);
        }
    }
}
