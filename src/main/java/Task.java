/**
 * This is a class to represent tasks.
 */

public class Task {
    /** Name of task */
    protected String name;
    /** Status of task, can either be complete or incomplete */
    protected boolean isCompleted;

    Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    Task(String name, boolean completed) {
        this.name = name;
        this.isCompleted = completed;
    }

    String getName() {
        return this.name;
    }

    String getType() {
        return "";
    }

    String getTime() {
        return "";
    }

    /**
     * Returns symbol used to represent status of task.
     *
     * @return o if task is complete, x if task is incomplete.
     */
    public String getStatusIcon() {
        return (this.isCompleted ? "o" : "x");
    }

    /**
     * Updates the status of the task to completed.
     *
     * @return Completed task with same name.
     */
    public Task completeTask() {
        return new Task(this.name, true);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.name;
    }
}
