package duke;

/**
 * Enum of all types of tasks.
 */
public enum TaskTypes {
    TODO {
        @Override
        public String toString() {
            return "T";
        }
    },

    DEADLINE {
        @Override
        public String toString() {
            return "D";
        }
    },

    EVENT {
        @Override
        public String toString() {
            return "E";
        }
    }

}
