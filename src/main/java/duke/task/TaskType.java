package duke.task;

/**
 * Represents task type with initial.
 */
public enum TaskType {
    TODO("T"), DEADLINE("D"), EVENT("E");

    private final String initial;

    /**
     * Initialises a task type with the initial.
     *
     * @param initial Initial.
     */
    TaskType(String initial) {
        this.initial = initial;
    }

    /**
     * Gets initial.
     *
     * @return Initial.
     */
    public String getInitial() {
        return initial;
    }
}
