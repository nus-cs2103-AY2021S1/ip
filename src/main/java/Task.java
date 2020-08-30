/**
 * Represents a Task.
 * A Task has a attribute taskDescription which describes the task.
 * A Task also has a boolean isCompleted which states if the task is completed.
 */
abstract class Task {
    protected String taskDescription;
    protected Boolean isCompleted;

    /**
     * Update task status.
     *
     * @param isCompleted Boolean value of is task completed.
     */
    public void updateStatus(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    abstract String getType();

    abstract String getDate();

    /**
     * Returns the task description.
     *
     * @return Task description.
     */
    public String getTask() {
        return taskDescription;
    }

    @Override
    public String toString() {
        String completedMarker = this.isCompleted ? "✓" : "✗";
        return String.format("[%s] %s", completedMarker, this.taskDescription);
    }
}
