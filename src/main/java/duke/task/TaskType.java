package duke.task;

/**
 * Represents all the possible types of a {@link Task}.
 */
public enum TaskType {
    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline");

    /**
     * Type of task.
     */
    private String type;

    /**
     * Instantiates a TaskType object.
     * @param type Type of a {@link Task}.
     */
    private TaskType(String type) {
        this.type = type;
    }

    /**
     * Returns a String representation of the {@link Task} type.
     * @return A String of the {@link Task} type.
     */
    public String getType() {
        return type;
    }
}
