package duke.task;

/**
 * Represents a task without any additional info.
 */
public class Todo extends Task {

    /**
     * Initializes a newly created Todo with a description.
     *
     * @param desc description of task.
     */
    public Todo(String desc) {
        super(desc);
    }

    /**
     * Initializes a newly created Todo with a description and whether it is done.
     *
     * @param desc description of task.
     * @param isDone whether task is done.
     */
    public Todo(String desc, boolean isDone) {
        super(desc, isDone);
    }

    /**
     * Sets this task as done.
     *
     * @return task set as done.
     */
    @Override
    public Task setAsDone() {
        boolean wasDone = this.isDone;
        Task doneTask = new Todo(this.description);
        doneTask.isDone = true;
        assert this.isDone == wasDone;
        return doneTask;
    }

    /**
     * Formats the task to a string to write in a file.
     *
     * @return formatted task.
     */
    public String formatTask() {
        return ("T | " + (isDone ? "V" : "X") + " | " + description);
    }

    /**
     * Returns a string representation of the task.
     *
     * @return string representation of task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
