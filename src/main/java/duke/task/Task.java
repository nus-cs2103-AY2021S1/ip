package duke.task;

/**
 * Represents a task with a description.
 */
public class Task {
    // Indicators
    public static final String STATUS_TICK = "[" + "\u2713" + "]";
    public static final String STATUS_CROSS = "[" + "\u2718" + "]";

    private String description;
    private String time;
    private boolean isDone;

    /**
     * Constructs a new task with the specified description.
     *
     * @param description Description of task.
     */
    public Task(String description, String... time) {
        this.description = description;

        if (time.length > 0) {
            this.time = time[0];
        } else {
            this.time = "";
        }

        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTime() {
        return this.time;
    }

    /**
     * Returns a tick if the task is done. Otherwise, returns a cross.
     *
     * @return A tick if the task is done and a cross if the task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? STATUS_TICK : STATUS_CROSS);
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns true if the task is done. Otherwise, returns false.
     *
     * @return true if the task is done and returns false if the task is not done.
     */
    public boolean hasDoneStatus() {
        return this.isDone;
    }

    /**
     * Determines if two different Task objects are duplicates of one another.
     *
     * @param task Possible duplicate task.
     * @return True is the tasks are duplicates. Otherwise, returns false.
     */
    public boolean equals(Task task) {
        boolean isEquals = this.description.equals(task.description) && task.time.equals(task.getTime());
        return isEquals;
    }

    /**
     * Returns a String representation of a task.
     *
     * @return String representation of a task.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
