package duke.task;

/**
 * Represents the different types a task can have.
 */
public enum TaskType {
    /**
     * Represents the type of a todo.
     */
    TODO {
        @Override
        public String toString() {
            return "T";
        }
    },
    /**
     * Represents the type of an event.
     */
    EVENT {
        @Override
        public String toString() {
            return "E";
        }
    },
    /**
     * Represents the type of a deadline.
     */
    DEADLINE {
        @Override
        public String toString() {
            return "D";
        }
    }
}
