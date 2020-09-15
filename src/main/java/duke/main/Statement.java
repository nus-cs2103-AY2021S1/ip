package duke.main;

/**
 * Consists of all pattern strings printed out by Duke.
 */
public enum Statement {
    GREET {
        @Override
        public String toString() {
            return "Wonderful! It is nice to see you!\n"
                    + "Is there anything I can help?";
        }
    },

    BYE {
        @Override
        public String toString() {
            return "It is nice to talk to you.\n"
                    + "Hope to see you again!";
        }
    },

    LIST {
        @Override
        public String toString() {
            return "Here are the tasks in your list:\n";
        }
    },

    DONE {
        @Override
        public String toString() {
            return "Nice! I've marked this task as done:\n";
        }
    },

    DELETE {
        @Override
        public String toString() {
            return "Noted. I've removed this task:\n";
        }
    },

    FIND {
        @Override
        public String toString() {
            return "Here are the matching tasks in your list\n";
        }
    },

    CLEAR {
        @Override
        public String toString() {
            return "Your tasks have been cleared.\n";
        }
    },

    UPDATE {
        @Override
        public String toString() {
            return "Got it. I've update the task\n";
        }
    },

    TASKADDED {
        @Override
        public String toString() {
            return "Got it. I've added this task\n";
        }
    },

    REPORT {
        @Override
        public String toString() {
            return "Now you have %d tasks in the list.";
        }
    }
}
