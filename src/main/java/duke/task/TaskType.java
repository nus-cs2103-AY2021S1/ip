package duke.task;

public enum TaskType {

    TODO {
        @Override
        public String toString() {
            return "T";
        }
    },
    EVENT {
        @Override
        public String toString() {
            return "E";
        }
    },
    DEADLINE {
        @Override
        public String toString() {
            return "D";
        }
    }

}
