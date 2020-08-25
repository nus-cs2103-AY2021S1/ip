package duke.task;

/**
 * Stores all methods and properties of a Task class.
 * Variables include description, task type, time and done status.
 */
public abstract class Task {
    private String description;
    private TaskType taskType;
    private String time;

    private boolean isDone;

    /**
     * Initialises the Task object.
     *
     * @param description Task details.
     * @param isDone Status of task - true if done, false otherwise.
     * @param taskType Type of Task.
     * @param time Time of the task.
     */
    protected Task(String description, boolean isDone, TaskType taskType, String time) {
        this.description = description;
        this.isDone = isDone;
        this.taskType = taskType;
        this.time = time;
    }

    private String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Retrieves the status of the Task.
     *
     * @return True iff task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Retrieves the String representation of the status of Task.
     *
     * @return String representation of Task status.
     */
    public String getStatus() {
        return isDone ? "Done" : "Not done";
    }

    /**
     * Retrieves the Task name in capitalized form.
     *
     * @return Task name in CAPS.
     */
    public String getTaskName() {
        return taskType.toString().toUpperCase();
    }

    /**
     * Retrieves the Task description.
     *
     * @return Task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieves the time of the Task.
     *
     * @return Time of Task.
     */
    public String getTime() {
        return time;
    }

    public boolean match(String query) {
        return description.contains(query);
    }

    /**
     * Returns the String representation of the Task.
     *
     * @return String representation of the Task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}