package duke.task;

public enum TaskType {
    TODO {
        @Override
        public String toString() {
            return "todo";
        }
    },
    DEADLINE {
        public String toString() {
            return "deadline";
        }
    },
    EVENT {
        @Override
        public String toString() {
            return "event";
        }
    },
    NONE

}
