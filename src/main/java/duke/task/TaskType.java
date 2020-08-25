package duke.task;

/**
 * Contains the three types of task, namely DEADLINE, EVENT and TODO.
 */
public enum TaskType {
    DEADLINE("deadline"),
    EVENT("event"),
    TODO("todo");

    private String taskName;

    TaskType(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Provides a string representation of the task type.
     *
     * @return String representation of the task type.
     */
    @Override
    public String toString() {
        return taskName;
    }
}
