/**
 * Types of TaskException are allowed.
 */
public enum TaskExceptionType {
    EMPTY {
        @Override
        public String toString() {
            return "is empty";
        }
    },
    FORMAT {
        @Override
        public String toString() {
            return "is of the wrong format";
        }
    },
    DONE {
        @Override
        public String toString() {
            return "already completed";
        }
    };
}
