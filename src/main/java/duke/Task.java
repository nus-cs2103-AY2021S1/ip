package duke;

import java.time.LocalDate;

/**
 * <h1>duke.Task</h1>
 * General task for the multiple types of task.
 * To-do, duke.Deadline and duke.Event classes inherit from this class.
 */
public abstract class Task {
    protected String description;
    private boolean isDone;

    /**
     * Constructor for duke.Task.
     * @param description Name of the task input by user.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This method is used to check if the task is done.
     * @return String This returns 'O' if done and 'X' if not done.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
        //return (isDone ? "O" : "X"); //return tick or X symbols
    }

    /**
     * Checks if task is done.
     * @return boolean True if task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    public boolean isNotDone() {
        return !isDone;
    }

    /**
     * Getter for description of task.
     * @return String This returns the name of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for task to mark task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Converts task to a string to be save in to-do.txt file
     * @return String A string containing task-type, doneStatus, descriptions and details.
     */
    public String convertToText() {
        String link = " >> ";
        return link + (this.isDone() ? "1" : "0") + link + this.getDescription();
    }

    public abstract boolean checkTask(LocalDate date);

    /**
     * Overridden toString method to output name, type and status of task.
     * @return String This returns a string.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
