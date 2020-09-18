/**
 * TaskType that is allowed.
 */
public enum TaskType {
    TODO {
        @Override
        public String getSymbol() {
            return "T";
        }

        @Override
        public String toString() {
            return "todo";
        }
    },
    DEADLINE {
        @Override
        public String getSymbol() {
            return "D";
        }

        @Override
        public String toString() {
            return "deadline";
        }
    },
    EVENT {
        @Override
        public String getSymbol() {
            return "E";
        }

        @Override
        public String toString() {
            return "event";
        }
    };

    public abstract String getSymbol();
}
