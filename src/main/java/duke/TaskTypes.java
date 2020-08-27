package duke;

public enum TaskTypes {
    TASK_TYPE_TODO {
        @Override
        public String toString() {
            return "T";
        }
    },

    TASK_TYPE_DEADLINE {
        @Override
        public String toString() {
            return "D";
        }
    },

    TASK_TYPE_EVENT {
        @Override
        public String toString() {
            return "E";
        }
    }

}
