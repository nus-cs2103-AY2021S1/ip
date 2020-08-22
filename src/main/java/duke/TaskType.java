package duke;

/**
 * Encapsulate a enum class that represents the type of a task.
 */
public enum TaskType {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    private final String type;

    /**
     * Constructs a TaskType object with the type given.
     *
     * @param type a string representing the type.
     */
    TaskType(String type) {
        this.type = type;
    }

    /**
     * Gets the type in the form of a string.
     *
     * @return a string representing the type.
     */
    public String getType() {
        return type;
    }

    /**
     * Determines if the type of task has a time/date attached.
     *
     * @param type the type of the task.
     * @return true if the type has a time attached and false otherwise.
     */
    public static boolean hasTime(String type) {
        return type.equals("event") || type.equals("deadline");
    }

    /**
     * Gets the TaskType object from a string representation of the type.
     * Returns null if no such object exists.
     *
     * @param type a string representing the type.
     * @return a TaskType object of the specified type and null if no such object exists.
     */
    public static TaskType valueOfStatus(String type) {
        for (TaskType e : values()) {
            if (e.type.equals(type)) {
                return e;
            }
        }
        return null;
    }
}
