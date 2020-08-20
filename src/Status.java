public enum Status {
    GREET {
        @Override
        public String toString() {
            return "    Wonderful! It is nice to see you! \n" +
                    "    Is there anything I can help?";
        }
    },

    BYE {
        @Override
        public String toString() {
            return "    It is nice to talk to you. \n" +
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
            return "    Nice! I've marked this task as done: \n" +
                    "      ";
        }
    },

    EXCESS {
        @Override
        public String toString() {
            return "    OOPS!!! It seems like you don't have this option \n" +
                    "    Enter list to see options";
        }
    },

    CLASSCASTEXCEPTION {
        @Override
        public String toString() {
            return "    The content is not of data type of String";
        }
    },

    NUMBERFORMATEXCEPTION {
        @Override
        public String toString() {
            return "    OOPS!!! The format of your index number is incorrect";
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
            return "    Got it. I've added this task \n" +
                    "      ";
        }
    },

    REPORT {
        @Override
        public String toString() {
            return "    Now you have %d tasks in the list.";
        }
    },

    INPUTFORMATEXCEPTION {
        @Override
        public String toString() {
            return "    OOPS!!! I'm sorry, but U don't know what that means :-(";
        }
    },

    INSUFFICIENTEXCEPTION {
        @Override
        public String toString() {
            return "    OOPS!!! The description of todo/deadline/event cannot be empty";
        }
    },

    NOTIMEEXCEPTION {
        @Override
        public String toString() {
            return "    OOPS!!! The time of a deadline/event cannot be empty";
        }
    }




}
