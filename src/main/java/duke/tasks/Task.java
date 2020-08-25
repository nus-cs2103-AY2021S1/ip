package duke.tasks;

/**
 * Represents tasks created by users.
 * To be inherited by Deadline, Event and ToDo classes.
 *
 */
public class Task {
    private boolean completed;
    private String taskName;

    /**
     * Creates Task with relevant task name.
     * @param name Task Name.
     */
    public Task(String name) {
        this.taskName = name;
        this.completed = false;
    }

    /**
     * Returns symbol corresponding to status of task.
     *
     * @return Current status of task;
     */
    protected String getStatus() {
        return (this.completed ? "\u2713" : "\u2718");
    }

    /**
     * Edits status of the task to completed.
     *
     */
    public void markDone() {
        this.completed = true;
    }

    /**
     * Returns task name of the selected task.
     *
     * @return Task name.
     */
    protected String getTaskName() {
        return this.taskName;
    }

    /**
     * Returns String representation of task to be displayed in task list.
     *
     * @return Representation of task in task list.
     */
    @Override
    public String toString() {
        return "[" + getStatus() + "] " + this.taskName;
    }

}
