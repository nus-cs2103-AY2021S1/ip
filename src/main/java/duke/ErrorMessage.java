package duke;

/**
 * Stores all error messages to output to user.
 */
class ErrorMessage {

    /**
     * Takes in a type of error, and outputs the corresponding error message.
     *
     * @param errorType The type of error.
     * @return Error message.
     */
    static String getErrorMessage(ErrorType errorType) {
        switch (errorType) {
        case FILE_IO:
            return "OOPS!!! An I/O file error has occurred.";
        case FILE_NOT_FOUND:
            return "OOPS!!! File cannot be found.";
        case WRONG_TASK_TYPE_IN_STORAGE:
            return "OOPS!!! Invalid task type in storage file, please type D/E/T.";
        case WRONG_DATE_FORMAT:
            return "OOPS!!! Pass in a date in yyyy-mm-dd";
        case INVALID_ORDER:
            return "OOPS!!! Please enter either asc or desc.";
        case INVALID_COMMAND:
            return "OOPS!!! I'm sorry, but I don't know what that means";
        case DONE_MISSING_INDEX:
            return "OOPS!!! Please add index to be done.";
        case SORT_MISSING_ORDER:
            return "OOPS!!! Please add the sorting order.";
        case DELETE_MISSING_INDEX:
            return "OOPS!!! Please add the index to be deleted.";
        case FIND_MISSING_KEYWORD:
            return "OOPS!!! Please add what you want to find.";
        default:
            assert false : "Should not reach here, this should be called by other "
                    + "classes only with the enumerations above";
            return "";
        }
    }

    /**
     * Takes in a type of error, and outputs the corresponding error message. Overloaded usage to handle variable tasks.
     *
     * @param errorType The type of error.
     * @param taskType Type of task.
     * @return Error message.
     */
    static String getErrorMessage(ErrorType errorType, String taskType) {
        switch (errorType) {
        case DEADLINE_AND_EVENT_MISSING_DATE:
            return "OOPS!!! The date of a/n " + taskType + " cannot be empty.";
        case TASK_MISSING_DESCRIPTION:
            return "OOPS!!! The description of a " + taskType + " cannot be empty.";
        default:
            assert false : "Should not reach here, this should be called by other "
                    + "classes only with the enumerations above";
            return "";
        }
    }
}
