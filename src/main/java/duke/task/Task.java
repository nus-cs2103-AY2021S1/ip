package duke.task;

import duke.exceptions.DukeException;

/**
 * Represents the task the user wants to do.
 */
public abstract class Task {
    protected String description;
    private boolean isDone;

    /**
     * @param description the description of the task
     * @throws DukeException if the the description of the task is left blank
     */
    public Task(String description) throws DukeException {
        if (description.isBlank()) {
            throw new DukeException("Please add a nice description to your task :)");
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Overloaded constructor for tasks from the database.
     *
     * @param doneStatus whether the task is done from the previous session.
     * @param description the description of the task stored in the database.
     */
    public Task(int doneStatus, String description) {
        if (doneStatus == 1) {
            this.isDone = true;
        }
        this.description = description;
    }

    /**
     * Sets the done state of the task.
     */
    public void setDone() {
        this.isDone = !isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getDoneStatus() {
        return this.isDone;
    }

    /**
     * Returns a string that is formatted having the type, done status, description, and time
     * of the task to be stored in the database.
     *
     * @return formatted string that is used for the creation of a task object.
     */
    public abstract String formatTaskForDatabase();

    @Override
    public String toString() {
        return isDone ? "[\u2713]" : "[\u2718]";
    }
}
