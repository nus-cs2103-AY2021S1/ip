package duke.task;

/**
 * Represents the different level of priority of a task.
 */
public enum Priority {
    HIGH("high"),
    MEDIUM("medium"),
    LOW("low");

    private String priority;

    Priority(String priority) {
        this.priority = priority;
    }

    /**
     * Gets the integer equivalence of a priority for ease of comparision.
     *
     * @return The number corresponds to the priority level of the task.
     */
    public int getEquivalentNumber() {
        if (this.priority.equals("high")) {
            return 1;
        } else if (this.priority.equals("medium")) {
            return 2;
        } else {
            return 3;
        }
    }

    @Override
    public String toString() {
        return this.priority;
    }
}
