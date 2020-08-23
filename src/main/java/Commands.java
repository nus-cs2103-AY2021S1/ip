public enum Commands {
    LIST {
        @Override
        public String toString() {
            return "list";
        }
    },
    BYE {
        @Override
        public String toString() {
            return "bye";
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
    },
    BLAH {
        @Override
        public String toString() {
            return "blah";
        }
    },
    TASK {
        @Override
        public String toString() {
            return "task";
        }
    },
    DONE {
        @Override
        public String toString() {
            return "done";
        }
    }
}
