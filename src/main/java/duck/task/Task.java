package duck.task;

import duck.ui.Colour;

import java.io.Serializable;

/**
 * A general Task class to be extended by other classes.
 * Provides the necessary default implementations of a Task.
 */
abstract public class Task implements Serializable {
    private String description;
    private boolean isDone;

    /**
     * Initializes the description and sets done to false.
     *
     * @param desc Description of Task.
     */
    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getDone() {
        return this.isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    /**
     * Gets the status of a Task which displays information about
     * whether the task is done and its description.
     *
     * @return Status of task.
     */
    public String getStatus() {
        String check = this.isDone ? "\u2713" : "\u2718";
        String status = "[" + check + "] " + this.getDescription();
        return this.isDone
                ? Colour.Green(status)
                : Colour.Red(status);
    }
}
