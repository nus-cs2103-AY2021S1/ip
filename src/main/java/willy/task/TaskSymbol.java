package willy.task;

/**
 * Responsible for the symbol allocation of each type of task.
 */
public enum TaskSymbol {
    TODO {
        @Override
        public String toString() {
            return "[T]";
        }
    },
    DEADLINE {
        @Override
        public String toString() {
            return "[D]";
        }
    },
    EVENT {
        @Override
        public String toString() {
            return "[E]";
        }
    };

}
