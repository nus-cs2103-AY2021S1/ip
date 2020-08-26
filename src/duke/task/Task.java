package duke.task;

/**
 * Class to represent the task object. This class
 * stores the task's name as well as the status of the task.
 */
public abstract class Task {
    private enum Status {
        DONE, ONGOING
    }

    private String taskName;
    private Status status;


    /**
     * Constructs a new Task object with the specified task name.
     * When a new Task object is created, the status is set to ongoing by default.
     *
     * @param taskName The task's name
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.status = Status.ONGOING;
    }

    /**
     * Gets the task's name.
     *
     * @return The task's name.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Returns true if the task has already been marked
     * as done and false otherwise.
     *
     * @return The status of the task.
     */
    public boolean isDone() {
        return this.status == Status.DONE;
    }

    /**
     * Sets the task's status to done.
     */
    public void setStatusToDone() {
        status = Status.DONE;
    }

    /**
     * Returns the string representation of the task. If the task is done,
     * it will be marked as O. Otherwise, it will be marked as X.
     *
     * @return a string representation of the task.
     */
    @Override
    public String toString() {
        if (status == Status.DONE) {
            return "[O] " + taskName;
        } else {
            return "[X] " + taskName;
        }
    }
}