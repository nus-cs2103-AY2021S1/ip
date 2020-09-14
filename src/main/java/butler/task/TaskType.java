package butler.task;

/**
 * Enums for task types.
 * There are three possible task types which are deadline, event and todo.
 */
public enum TaskType {
    TODO, DEADLINE, EVENT;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
