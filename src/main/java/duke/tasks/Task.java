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
     * Constructor for Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for Task with additional parameter.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

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
