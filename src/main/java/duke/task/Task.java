package duke.task;

import duke.exceptions.DukeException;

/**
 * Represents the task the user wants to do.
 */
public abstract class Task {
    protected Priority priority;
    protected String description;
    private boolean isDone;

    public enum Priority {
        HIGH, MID, LOW
    }


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
        this.priority = Priority.MID;
    }

    /**
     * Overloaded constructor for tasks from the database.
     *
     * @param doneStatus  whether the task is done from the previous session.
     * @param description the description of the task stored in the database.
     */
    public Task(String priority, int doneStatus, String description) {
        if (doneStatus == 1) {
            this.isDone = true;
        }
        this.description = description;
        this.priority = Priority.valueOf(priority.toUpperCase());
    }

    /**
     * Sets the done state of the task.
     */
    public void setDone() {
        this.isDone = !isDone;
    }

    public void setPriority(String priority) {
        switch (priority) {
        case "high":
            this.priority = Priority.HIGH;
            break;
        case "low":
            this.priority = Priority.LOW;
            break;
        default:
            this.priority = Priority.MID;
        }
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getDoneStatus() {
        return this.isDone;
    }

    /**
     * Returns a string that is formatted having the taskType, done status, description, and time
     * of the task to be stored in the database.
     *
     * @return formatted string that is used for the creation of a task object.
     */
    public abstract String formatTaskForDatabase();

    @Override
    public String toString() {
        String status = isDone ? "[\u2713]" : "[\u2718]";
        return status + " " + description;
    }
}
