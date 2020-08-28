package duke.task;

/** Task is a class for each task specified from the user commands */
public abstract class Task {
    protected final String name;
    protected final boolean isDone;

    /**
     * Initializes a task instance via its name.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Overloads the initialization of task by checking its name and whether it is done.
     *
     * @param name The name of the task.
     * @param isDone The boolean indicating if the task is done.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Gets the name of the task.
     *
     * @return A String showing the name of the task.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the status of the task.
     *
     * @return A boolean indicating if the task is done.
     */
    public boolean isDoneTask() {
        return isDone;
    }

    /**
     * Marks the status of the task as done.
     *
     * @return A new Task object with the status updated to "done".
     */
    public abstract Task complete();

    /**
     * Formats the task for data output display in the duke.txt file.
     *
     * @return A string to represent the task.
     */
    public abstract String format();

    /**
     * Displays the task object as a string
     *
     * @return The status of the task followed by the name
     */
    @Override
    public String toString() {
        String checkbox = "[" + (this.isDoneTask() ? "\u2713" : "\u2718") + "]";
        return checkbox + " " + this.getName();
    }
}
