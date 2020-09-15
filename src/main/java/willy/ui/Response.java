package willy.ui;

public enum Response {
    //    private final String NON_EXISTENT_TASK_MESSAGE = "Task does not exist, please check the list again or add
    //    the task first ~";
//    private final String TASK_DONE_MESSAGE = "Niceee I've marked this task as done!";
//    private final String NO_MATCHING_TASK_MESSAGE = "There are no matching tasks found in the list. Try something
//    else?";
//    private final String MATCHING_TASK_MESSAGE = "Here are the matching tasks in your list:";
//    private final String ADD_TASK_RESPONSE = "Ay here is the task you just added:\n";
//    private final String REMOVE_TASK_RESPONSE = "Okai here is the task you just deleted:\n";
//    private final String EMPTY_LIST_RESPONSE = "\tThere is no task in your list:>\n";
//    private final String NON_EMPTY_LIST_RESPONSE = "\tHere are the tasks in your list to jolt ur memory:>\n";
//    private final String UPDATE_TASK_RESPONSE = "Okay, Here's the task you just updated:\n";
//    private final String MISSING_INFO_MESSAGE = "Hmmm are you missing description/deadline of the task? \n\tCheck
//    and" +
//            " try again?";
//    private final String NO_TASK_MESSAGE = "Please add in a task!";
//    private final String NO_SENSE_MESSAGE = "Hmmm sorry I'm not sure what you are saying, try something else?:(";
    INCOMPLETE_INFO {
        @Override
        public String toString() {
            return "Hmmm are you missing description/deadline of the task? \n\tCheck and try again?";
        }
    },
    NO_TASK {
        @Override
        public String toString() {
            return "Task not found, please check the list again or add the task first";
        }
    },
    ADD_TASK {
        @Override
        public String toString() {
            return "Ay here is the task you just added:\n";
        }
    },
    NON_MATCHING_TASK {
        @Override
        public String toString() {
             return "There are no matching tasks found in the list. Try something else?";
        }
    },
    MATCHING_TASK {
        @Override
        public String toString() {
            return "Okay, Here's the task you just updated:\n";
        }
    },
    REMOVE_TASK {
        @Override
        public String toString() {
            return "Okai here is the task you just deleted:\n";
        }
    },
    DONE_TASK {
        @Override
        public String toString() {
            return "Niceee I've marked this task as done!";
        }
    },
    UPDATE_TASK {
        @Override
        public String toString() {
            return "Okay, Here's the task you just updated:\n";
        }
    },
    EMPTY_LIST {
        @Override
        public String toString() {
            return "\tThere is no task in your list:>\n";
        }
    },
    NON_EMPTY_LIST {
        @Override
        public String toString() {
            return "\tHere are the tasks in your list to jolt ur memory:>\n";
        }
    },
    NO_SENSE {
        @Override
        public String toString() {
            return "Hmmm sorry I'm not sure what you are saying, try something else?:(";
        }
    }
}
