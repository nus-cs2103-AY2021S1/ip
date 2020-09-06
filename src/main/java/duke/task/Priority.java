package duke.task;

import java.util.Arrays;

public enum Priority {

    CRITICAL(1),
    HIGH(2),
    MID(3),
    LOW(4),
    UNCLASSIFIED(5);

    private int priorityValue;
    private Priority(final int priorityValue) {
        this.priorityValue = priorityValue;
    }

    /**
     * Returns the priority in Integer value, 1 to 5, where 1 is the highest in priority.
     *
     * @return priority in Integer form.
     */
    public int getPriorityValue() {
        return this.priorityValue;
    }

    /**
     * Performs a reverse lookup by getting the Priority type from Integer valye.
     *
     * @param priorityValue priority value in Integer.
     * @return priority type.
     */
    public static Priority getPriority(int priorityValue) {
        return Arrays.stream(values())
                .filter(x -> x.priorityValue == priorityValue)
                .findFirst()
                .get();
    }

    /**
     * Returns the name of this priority.
     *
     * @return name of priority.
     */
    public String toString() {
        return this.name();
    }
}
