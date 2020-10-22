package duke.classes;

/**
 * Enum class for categorising the commands given to the scanner.
 */
public enum Command {
    FIND {
        @Override
        public String toString() {
            return "find";
        }
    },
    LIST {
        @Override
        public String toString() {
            return "list";
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
    },
    BYE {
        @Override
        public String toString() {
            return "bye";
        }
    },
    TAG {
        @Override
        public String toString() {
            return "tag";
        }
    }, INVALID {
        @Override
        public String toString() {
            return "invalid command";
        }
    }

}
