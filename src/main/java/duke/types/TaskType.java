package duke.types;

/**
 * Encapsulate a enum class that represents the type of a task.
 * The class include three task types: todo, deadline and event
 */
public enum TaskType {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    private final String type;

    /**
     * Constructs a TaskType object with the type of the task given.
     *
     * @param taskType a string representing the type of the task.
     */
    TaskType(String taskType) {
        type = taskType;
    }

    /**
     * Gets the type of the task in the form of a string.
     *
     * @return a string representing the type.
     */
    public String getType() {
        return type;
    }

    /**
     * Determines if the type of task has a time/date attached.
     *
     * @param taskType the type of the task.
     * @return true if the type has a time attached and false otherwise.
     */
    public static boolean hasTime(String taskType) {
        return taskType.equals("event") || taskType.equals("deadline");
    }

    /**
     * Gets the TaskType object from a string representation of the type of the task.
     * Returns null if no such object exists.
     *
     * @param taskType a string representing the type of the task.
     * @return a TaskType object of the specified type and null if no such object exists.
     */
    public static TaskType valueOfType(String taskType) {
        for (TaskType e : values()) {
            if (e.type.equals(taskType)) {
                return e;
            }
        }
        return null;
    }
}
