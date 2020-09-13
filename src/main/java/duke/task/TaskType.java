package duke.task;

/**
 * Contains the three types of task, namely DEADLINE, EVENT and TODO.
 */
public enum TaskType {
    DEADLINE,
    EVENT,
    TODO;

    public String toLower() {
        return toString().toLowerCase();
    }
}
