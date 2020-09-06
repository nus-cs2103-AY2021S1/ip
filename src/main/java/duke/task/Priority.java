package duke.task;

import duke.exception.DukeException;

import java.util.Arrays;
import java.util.NoSuchElementException;

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
     * @throws DukeException when invalid priority number is given (range allowed: 1-5)
     */
    public static Priority getPriority(int priorityValue) throws DukeException {
        try {
            return Arrays.stream(values())
                    .filter(x -> x.priorityValue == priorityValue)
                    .findFirst()
                    .get();
        } catch (NoSuchElementException e) {
            throw new DukeException("Invalid Priority Given...");
        }
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
