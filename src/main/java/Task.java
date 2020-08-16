/**
 * Encapsulates a to-do list type of task for the Duke program. A task is
 * inclusive of a textual description and a boolean flag to mark whether it has
 * been completed or not.
 */
public class Task {
    protected String description; // Describes the task
    protected boolean isDone; // Marks whether the task is completed or not
    protected String taskType; // To-Do, Deadline or Event task

    public Task(String description, String taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return taskType + "[" + getStatusIcon() + "] " + description;
    }
}
