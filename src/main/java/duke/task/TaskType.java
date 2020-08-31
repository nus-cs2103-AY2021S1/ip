package duke.task;

/**
 * Represents tasks that can be used.
 */
public enum TaskType {
    DEADLINE("D"),
    EVENT("E"),
    TODO("T");

    private final String shortForm;

    TaskType(String taskType) {
        this.shortForm = taskType;
    }

    /**
     * Gets the short form string representation of the task type.
     * @return The short form string representation of the task type.
     */
    public String getShortForm() {
        return this.shortForm;
    }
}
