package duke.task;

/**
 * Task is a class for each duke.task specified from
 * the user commands.
 *
 */
public class Task {
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
     * Get the name of the duke.task.
     *
     * @return a String showing the name of the duke.task.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the status of the duke.task.
     *
     * @return a boolean indicating if the duke.task is done.
     */
    public boolean isDoneTask() {
        return isDone;
    }

    /**
     * Mark the status of the duke.task as done.
     *
     * @return a new Task object with the status updated to "done".
     */
    public Task complete() {
        return new Task(this.name, true);
    }

    /**
     * Display the duke.task object as a string
     *
     * @return the status of the duke.task followed by the name
     */
    @Override
    public String toString() {
        String checkbox = "[" + (this.isDoneTask() ? "\u2713" : "\u2718") + "]";
        return checkbox + " " + this.getName();
    }
}
