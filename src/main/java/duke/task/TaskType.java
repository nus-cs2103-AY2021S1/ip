package duke.task;

/**
 * The enum TaskType denotes the type of task.
 *
 * @author Alvin Chee
 */
public enum TaskType {
    TODO ('T'),
    DEADLINE ('D'),
    EVENT ('E');

    private char taskSymbol;
    /**
     * Constructs a task type.
     *
     * @param taskSymbol  Symbol representing the type of task.
     */
    TaskType(char taskSymbol) {
        this.taskSymbol = taskSymbol;
    }

    /**
     * Returns the task symbol.
     */
    public char returnTaskSymbol() {
        return taskSymbol;
    }

    @Override
    public String toString() {
        return "[" + taskSymbol + "]";
    }
}
