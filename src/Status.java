public enum Status {
    GREET {
        @Override
        public String toString() {
            return "    Wonderful! It is nice to see you!\n" +
                    "    Is there anything I can help?";
        }
    },

    BYE {
        @Override
        public String toString() {
            return "    It is nice to talk to you.\n" +
                    "    Hope to see you again!";
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
            return "    Nice! I've marked this task as done:\n" +
                    "      ";
        }
    },

    DELETE {
        @Override
        public String toString() {
            return "    Noted. I've removed this task:\n" +
                    "      ";
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

    TASKADDED {
        @Override
        public String toString() {
            return "    Got it. I've added this task\n" +
                    "      ";
        }
    },

    REPORT {
        @Override
        public String toString() {
            return "    Now you have %d tasks in the list.";
        }
    },


    FINISHED {
        @Override
        public String toString() {
            return "\u2713";
        }
    }


}
