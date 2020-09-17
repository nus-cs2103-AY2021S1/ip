package duke.tasks;

import duke.exceptions.DukeException;

/**
 * Represents a Priority.
 */
public enum Priority {
    LOW(0),
    MEDIUM(1),
    HIGH(2);

    private final int value;

    Priority(int value) {
        this.value = value;
    }

    /**
     * Parses the integer representation of a priority and returns a priority object.
     *
     * @param value Integer representation of the priority.
     * @return Priority corresponding to the integer representation.
     * @throws DukeException Invalid priority is passed.
     */
    public static Priority parseIntValue(int value) throws DukeException {
        switch (value) {
        case 0:
            return Priority.LOW;
        case 1:
            return Priority.MEDIUM;
        case 2:
            return Priority.HIGH;
        default:
            throw new DukeException("Invalid priority value specified, has to be between 0-2");
        }
    }

    /**
     * Returns a user-readable priority string.
     *
     * @return User-readable priority string.
     */
    @Override
    public String toString() {
        switch (this.value) {
        case 0:
            return "!";
        case 1:
            return "!!";
        case 2:
            return "!!!";
        default:
            throw new IllegalArgumentException();
        }
    }

    /**
     * Returns a priority string readable by storage.
     *
     * @return Storage-safe priority string.
     */
    public String toSaveString() {
        switch (this.value) {
        case 0:
            return "0";
        case 1:
            return "1";
        case 2:
            return "2";
        default:
            throw new IllegalArgumentException();
        }
    }
}
