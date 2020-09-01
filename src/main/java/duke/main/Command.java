package main.java.duke.main;

/**
 * This is an enum class that contains
 * all String for the commands.
 */
public enum Command {
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

    BYE {
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

    DONE {
        @Override
        public String toString() {
            return "done";
        }
    },

    DELETE {
        @Override
        public String toString() {
            return "delete";
        }
    },

    FIND {
        @Override
        public String toString() {
            return "find";
        }
    },

    CLEAR {
        @Override
        public String toString() {
            return "clear";
        }
    }
}
