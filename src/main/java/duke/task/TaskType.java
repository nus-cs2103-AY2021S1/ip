package duke.task;

/**
 * Contains the three types of task, namely DEADLINE, EVENT and TODO.
 */
public enum TaskType {
    DEADLINE,
    EVENT,
    TODO;

    /**
     * Obtains a lower-cased representation of the {@code TaskType}.
     *
     * @return TaskType in lower-cased.
     */
    public String toLowerCase() {
        return toString().toLowerCase();
    }
}
