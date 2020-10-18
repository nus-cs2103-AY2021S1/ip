package duke.tasks;

/**
 * Represents different type of a task.
 * @version 1.0
 */
public enum TaskType {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    private String type;

    TaskType(String type) {
        this.type = type;
    }

    /**
     * Returns the value of the TaskType.
     *
     * @return value of the TaskType.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Returns the corresponding TaskType object from the specified value if found.
     *
     * @param value The specified value to find in TaskType.
     * @return The corresponding TaskType object.
     */
    public static TaskType valueToType(String value) {
        for (TaskType t : values()) {
            if (t.type.equals(value)) {
                return t;
            }
        }
        return null;
    }
}
