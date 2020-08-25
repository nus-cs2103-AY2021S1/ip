package duke.task;

/**
 * The enum TaskType denotes the type of task.
 *
 * @author Alvin Chee
 */
public enum TaskType {
    TODO ("[T]"),
    DEADLINE ("[D]"),
    EVENT ("[E]");

    private String taskSymbol;

    /**
     * Constructs a task type.
     *
     * @param taskSymbol  Symbol representing the type of task.
     */
    TaskType(String taskSymbol) {
        this.taskSymbol = taskSymbol;
    }

    @Override
    public String toString() {
        return taskSymbol;
    }
}
