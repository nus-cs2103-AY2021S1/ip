public enum TaskType {
    TERMINATION {
        @Override
        public String toString() {
            return "bye";
        }
    },
    LIST {
        @Override
        public String toString() {
            return "list";
        }
    },
    COMPLETE_TASK {
        @Override
        public String toString() {
            return "done";
        }
    },
    TODO {
        @Override
        public String toString() {
            return "todo";
        }
    },
    DEADLINE {
        @Override
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
    DELETE {
        @Override
        public String toString() {
            return "delete";
        }
    };

    public static TaskType getCommand(String s) {
        switch (s) {
        case "T":
            return TODO;
        case "D":
            return DEADLINE;
        case "E":
            return EVENT;
        case "DEL":
            return DELETE;
        case "DONE":
            return COMPLETE_TASK;
        case "LIST":
            return LIST;
        case "BYE":
            return TERMINATION;
        default:
            throw new IllegalArgumentException("Bad argument for getCommand");
        }
    }
}
