package duke.task;

/**
 * Represents the type of the task.
 */
public enum TaskType {
    TODO("[T]"), DEADLINE("[D]"), EVENT("[E]");

    private final String bracketedTaskType;

    /**
     * Class constructor.
     *
     * @param bracketedTaskType The symbol for the Type of Task.
     */
    TaskType(String bracketedTaskType) {
        this.bracketedTaskType = bracketedTaskType;
    }

    /**
     * Returns the String representation of the Type of Task.
     *
     * @return String representation of the Type of Task.
     */
    @Override
    public String toString() {
        return bracketedTaskType;
    }
}
