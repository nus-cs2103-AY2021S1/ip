package duke.main;

/**
 * This enum class consists of
 * all pattern strings printed out
 * by Duke except the exceptions.
 */
public enum Statement {
    GREET {
        @Override
        public String toString() {
            return "    Wonderful! It is nice to see you!\n"
                    + "    Is there anything I can help?";
        }
    },

    BYE {
        @Override
        public String toString() {
            return "    It is nice to talk to you.\n"
                    + "    Hope to see you again!\n"
                    + "    To restart, relaunch Duke";
        }
    },

    LIST {
        @Override
        public String toString() {
            return "    Here are the tasks in your list:\n";
        }
    },

    DONE {
        @Override
        public String toString() {
            return "    Nice! I've marked this task as done:\n"
                    + "      ";
        }
    },

    DELETE {
        @Override
        public String toString() {
            return "    Noted. I've removed this task:\n"
                    + "      ";
        }
    },

    FIND {
        @Override
        public String toString() {
            return "    Here are the matching tasks in your list\n";
        }
    },

    CLEAR {
        @Override
        public String toString() {
            return "    Your tasks have been cleared.\n";
        }
    },

    TASKADDED {
        @Override
        public String toString() {
            return "    Got it. I've added this task\n"
                    + "      ";
        }
    },

    REPORT {
        @Override
        public String toString() {
            return "    Now you have %d tasks in the list.";
        }
    }
}
