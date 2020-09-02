package duke.tasks;

/**
 * The Task class implements a Task with task description.
 * Task status can be done or not done.
 *
 * @author Amy Lim Zhi Ting
 * @version v0.1
 */
public class Task {
    protected String taskName;
    protected boolean isDone;

    /**
     * Initialises a Task object with the String taskName which describes
     * the task. Task objects include Todo, Deadline and Event.
     * @param taskName string description of the task
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "1" : "0"); //return 1 or 0 symbols
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + taskName;
    }

    /**
     * Returns string of this task to be stored in the hard disk.
     *
     * @return String task description to be stored in hard disk.
     */
    public String storedTaskString() {
        return this.getStatusIcon() + "@" + taskName;
    }
}
