package duke.task;

/**
 * Represents a Task.
 */
public class Task {
    protected boolean isCompleted;
    protected String taskName;

    protected Task(String name, boolean isCompleted) {
        this.taskName = name;
        this.isCompleted = isCompleted;
    }

    /**
     * Creates a new Task.
     * The completion status is false by default.
     * @param name A string representing the name of the new task.
     * @return The new task created.
     */
    public static Task newTask(String name){
        return new Task(name, false);
    }

    /**
     * Creates a Task using information in storage.
     * The Task created is an already previously existing task with its details recorded in storage.
     * Usually called when starting up the application, to populate the TaskList.
     * @param name A string representing the name of the existing task.
     * @param isCompleted A boolean value representing whether the task has been completed.
     * @return The existing task created.
     */
    public static Task existingTask(String name, boolean isCompleted){
        return new Task(name, isCompleted);
    }

    /**
     * Gets the name of the task.
     * @return A string representing the name of the task.
     */
    public String getTaskName(){
        return this.taskName;
    }

    /**
     * Gets the completion status of the task.
     * @return A boolean value representing whether the task has been completed.
     */
    public boolean getIsCompleted(){
        return this.isCompleted;
    }

    /**
     * Sets the completion status of the task as true.
     * @return The task that was marked as completed.
     */
    public Task markAsDone(){
        this.isCompleted = true;
        return this;
    }

    /**
     * Gets the symbol that represents the completion status of the task.
     * @return The symbol that represents the completion status of the task.
     */
    public String getStatusIcon() {
        return (isCompleted ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Gets whether the task date is set as today.
     * This will be overridden in subclasses that contain a Date field.
     * This is the default implementation for subclasses that do not contain such a field.
     * @return false (default implementation).
     */
    public boolean isToday(){
        return false;
    }

    /**
     * Gets the string representation of the task object for printing.
     * This contains the completion status and the task name.
     * @return A string representation of the task for printing.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.taskName;
    }

    /**
     * Gets the string representation of the task object to be saved in storage.
     * This contains the completion status and the task name.
     * Completion status is represented by a 1 or 0.
     * The different fields are separated by a "|" character.
     * @return A string representation of the task object to be saved in storage.
     */
    public String toSaveString(){
        return (this.isCompleted ? "1" : "0") + " | " + this.taskName;
    }
}
