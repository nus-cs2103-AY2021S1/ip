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
            return "PIII!!! An I/O file error has occurred. \uD83D\uDC80";
        case FILE_NOT_FOUND:
            return "PIII!!! File cannot be found. \uD83D\uDC80";
        case WRONG_TASK_TYPE_IN_STORAGE:
            return "PIII!!! Invalid task type in storage file, please type D/E/T. \uD83D\uDC80";
        case WRONG_DATE_FORMAT:
            return "PIII!!! Pass in a date in yyyy-mm-dd. \uD83D\uDC80";
        case INVALID_ORDER:
            return "PIII!!! Please enter either asc or desc. \uD83D\uDC80";
        case INVALID_COMMAND:
            return "PIII!!! I'm sorry, but I don't know what that means. \uD83D\uDC80";
        case DONE_MISSING_INDEX:
            return "PIII!!! Please add index to be done. \uD83D\uDC80";
        case SORT_MISSING_ORDER:
            return "PIII!!! Please add the sorting order. \uD83D\uDC80";
        case DELETE_MISSING_INDEX:
            return "PIII!!! Please add the index to be deleted. \uD83D\uDC80";
        case FIND_MISSING_KEYWORD:
            return "PIII!!! Please add what you want to find. \uD83D\uDC80";
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
            return "PIII!!! The date of a/n " + taskType + " cannot be empty. \uD83D\uDC80";
        case TASK_MISSING_DESCRIPTION:
            return "PIII!!! The description of a " + taskType + " cannot be empty. \uD83D\uDC80";
        default:
            assert false : "Should not reach here, this should be called by other "
                    + "classes only with the enumerations above";
            return "";
        }
    }
}
