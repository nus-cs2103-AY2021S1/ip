package main.java.farrell.duke;

/**
 * Enumeration of the types of tasks handled by the program.
 */
public enum TaskType {
    TODO,
    EVENT,
    DEADLINE;

    /**
     * Maps a string to its corresponding TaskType value.
     *
     * @param string The string to be mapped.
     * @return The TaskType value associated with the string.
     */
    public static TaskType enumFromString(String string) {
        return TaskType.valueOf(string.toUpperCase());
    }
}
