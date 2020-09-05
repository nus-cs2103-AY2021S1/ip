package focus.task;

import java.time.LocalDateTime;

/** Represents an abstract Task class to allow different types of tasks to inherit. */
public abstract class Task {
    /** Description of task. */
    protected String taskDescription;
    /** Checks if task is completed or not. */
    protected boolean isDone;
    /** '0' to represent incomplete, '1' to represent completed. */
    protected String completed;

    /**
     * Creates a Task with a given task name, with it being not done yet.
     *
     * @param taskDescription Description of task.
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
        this.completed = "0";
    }

    /**
     * Returns the description of task.
     *
     * @return Task's description.
     */
    public String getTaskName() {
        return this.taskDescription;
    }

    /**
     * Returns a tick or a cross based on whether the task is done or not done.
     *
     * @return Tick or cross symbols.
     */
    public String getStatusIcon() {
        return isDone
                ? "\u2713"
                : "\u2718";
    }

    /** Marks the task as done. */
    public void markAsDone() {
        this.isDone = true;
        this.completed = "1";
    }

    /**
     * Converts Task to string form to be saved in user's files.
     *
     * @return String representation of task.
     */
    public abstract String taskToText();

    /**
     * Gets LocalDateTime of tasks.
     *
     * @return LocalDateTime if task is not a To-Do task.
     */
    public abstract LocalDateTime getDeadline();

    /**
     * Returns string format of Task.
     *
     * @return A string representation of Task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.taskDescription;
    }
}
