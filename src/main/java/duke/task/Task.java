package duke.task;

/**
 * Task is a class for each task specified from the user commands.
 */
public abstract class Task {
    protected final String name;
    protected final boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Get the name of the task.
     *
     * @return a String showing the name of the task.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the status of the task.
     *
     * @return a boolean indicating if the task is done.
     */
    public boolean isDoneTask() {
        return isDone;
    }

    /**
     * Mark the status of the task as done.
     *
     * @return a new Task object with the status updated to "done".
     */
    public abstract Task complete();

    /**
     * Format the task for data output display in the duke.txt file.
     *
     * @return a string to represent the task.
     */
    public abstract String format();

    /**
     * Display the task object as a string
     *
     * @return the status of the task followed by the name
     */
    @Override
    public String toString() {
        String checkbox = "[" + (this.isDoneTask() ? "\u2713" : "\u2718") + "]";
        return checkbox + " " + this.getName();
    }
}
