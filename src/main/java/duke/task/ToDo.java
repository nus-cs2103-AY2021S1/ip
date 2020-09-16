package duke.task;

/**
 * Encapsulates the to-do task type. A to-do task only has a description of the task.
 */
public class ToDo extends Task {

    /** The task type symbol that is used in the program to represent a to-do task */
    public static final String TASK_TYPE_SYMBOL = "[T]";

    private static final String TASK_TYPE_NAME = "todo";

    /**
     * Creates and initializes a to-do task that has not been completed by default.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        this(description, false);
    }

    /**
     * Creates and initializes a to-do task that can be marked as completed.
     *
     * @param description The description of the task.
     * @param isDone Marks whether the task has been completed or not.
     */
    public ToDo(String description, boolean isDone) {
        super(description,
                "",
                "",
                TASK_TYPE_SYMBOL,
                TASK_TYPE_NAME,
                isDone);
    }
}
