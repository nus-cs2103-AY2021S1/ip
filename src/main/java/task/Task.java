package task;

public abstract class Task {
    private int hasCompletedInt;
    private String name;
    private TaskType taskType;

    /**
     * Default Constructor for Task
     *
     * @param name Name of Task.
     */
    public Task(String name, TaskType taskType) {
        this.hasCompletedInt = 0;
        this.name = name;
        this.taskType = taskType;
    }

    /**
     * Alternative Constructor for Task
     *
     * @param name            Name of Task.
     * @param taskType        Type of Task.
     * @param hasCompletedInt boolean to determine whether task is completed.
     */
    public Task(String name, TaskType taskType, int hasCompletedInt) {
        this.hasCompletedInt = hasCompletedInt;
        this.taskType = taskType;
        this.name = name;
    }

    public void markAsDone() {
        this.hasCompletedInt = 1;
    }

    public int getHasCompletedInt() {
        return hasCompletedInt;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return String.format("[%s] %s", this.hasCompletedInt == 1 ? "✓" : "✗", this.name);
    }

    public abstract String getFormattedString();

    public enum TaskType {
        TODO, EVENT, DEADLINE;
    }

    public TaskType getTaskType() {
        return taskType;
    }
}

