/**
 * Types of TaskException are allowed.
 */
public enum TaskExceptionType {
    IDENTIFY {
        @Override
        public String toString() {
            return "cannot be identified";
        }
    },
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
    };
}
