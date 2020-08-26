package duke;

/**
 * Represents Enumeration class for types of commands.
 */
public enum Type {
    TODO {
        /**
         * Returns string representation of ToDo task.
         *
         * @return String representation of ToDo task.
         */
        public String toString() {
            return "ToDo";
        }
    }, DEADLINE {
        /**
         * Returns string representation of Deadline task.
         *
         * @return String representation of Deadline task.
         */
        public String toString() {
            return "Deadline";
        }
    }, EVENT {
        /**
         * Returns string representation of Event task.
         *
         * @return String representation of Event task.
         */
        public String toString() {
            return "Event";
        }
    }
}
