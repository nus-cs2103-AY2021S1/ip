package duke.tasks;

import duke.exceptions.TaskCompletedException;

/**
 * @author Damith C. Rajapakse
 * Reused from https://nus-cs2103-ay2021s1.github.io/website/schedule/week2/project.html with modifications
 *
 * Class to initiate Task and provides the basic behaviors for its children classes.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Initializes  Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Initializes Task with additional parameter compared to the first constructor.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the current task as done.
     */
    public void markAsDone() throws TaskCompletedException {
        if (isDone) {
            throw new TaskCompletedException();
        }
        this.isDone = true;
    }

    public String getDescription() {
        return description;
    }

    public boolean isTaskDone() {
        return isDone;
    }

    public String toString() {
        return '[' + getStatusIcon() + "] " + description;
    }
}
