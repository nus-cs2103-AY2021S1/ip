package duke;

public enum Tasktype {
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
