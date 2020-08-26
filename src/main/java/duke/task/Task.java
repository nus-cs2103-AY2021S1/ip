package duke.task;

import duke.exceptions.DukeException;

/**
 * Represents the task the user wants to do.
 */
public abstract class Task {
    protected String description;
    private boolean isDone;

    /**
     * @param s the description of the task
     * @throws DukeException if the the description of the task is left blank
     */
    public Task(String s) throws DukeException {
        if (s.isBlank()) {
            throw new DukeException("Please add a nice description to your duke.task :)");
        }
        this.description = s;
        this.isDone = false;
    }

    /**
     * Overloaded constructor for tasks from the database.
     *
     * @param doneStatus whether the task is done from the previous session.
     * @param s the description of the task stored in the database.
     */
    public Task(int doneStatus, String s) {
        if (doneStatus == 1) {
            this.isDone = true;
        }
        this.description = s;
    }

    /**
     * Sets the done state of the task.
     */
    public void setDone() {
        this.isDone = !isDone;
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
        String isChecked = (isDone ? "[\u2713]" : "[\u2718]");
        return isChecked;
    }
}
