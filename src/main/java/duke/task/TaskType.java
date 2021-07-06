package duke.task;

/**
 * Represents all valid tasks that can be used.
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
     *
     * @return String representation of the task type in short form.
     */
    public String getShortForm() {
        return this.shortForm;
    }
}
