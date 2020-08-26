/**
 * This is a class to represent tasks.
 */

public class Task {
    /** Name of task */
    String name;
    /** Status of task, can either be complete or incomplete */
    boolean completed;

    Task(String name) {
        this.name = name;
        this.completed = false;
    }

    Task(String name, boolean completed) {
        this.name = name;
        this.completed = completed;
    }

    String getName() { return this.name; }

    String getType() { return ""; }

    String getTime() { return ""; }

    /**
     * Returns symbol used to represent status of task.
     *
     * @return o if task is complete, x if task is incomplete.
     */
    public String getStatusIcon() {
        return (this.completed ? "o" : "x"); //return O or X symbols
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
