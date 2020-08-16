/**
 * Encapsulates a to-do list type of task for the Duke program. A task is
 * inclusive of a textual description and a boolean flag to mark whether it has
 * been completed or not.
 */
public class Task {
    protected String description; // Describes the task
    protected boolean isDone; // Marks whether the task is completed or not
    protected String taskTypeSymbol; // [T], [D] or [E]
    protected String taskTypeName; // To-Do, Deadline or Event task

    public Task(String description, String taskTypeSymbol, String taskTypeName)
            throws WrongFormatException {
        if (description.isEmpty()) {
            throw new WrongFormatException(taskTypeName);
        }
        this.description = description;
        this.isDone = false;
        this.taskTypeSymbol = taskTypeSymbol;
        this.taskTypeName = taskTypeName;
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : "✘"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return taskTypeSymbol + "[" + getStatusIcon() + "] " + description;
    }
}
