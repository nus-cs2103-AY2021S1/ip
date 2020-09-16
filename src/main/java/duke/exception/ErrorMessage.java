package duke.exception;

public enum ErrorMessage {
    INVALID_COMMAND_TYPE("Please enter a valid command type!"),
    INVALID_COMMAND_FORMAT("Please enter your command in the correct format!"),
    INVALID_DATE_TIME_FORMAT("Please enter a valid date and time in the format 'DD-MM-YYYY HHMM'!"),
    INVALID_TASK_NUMBER("Please enter a valid task number!"),
    INVALID_QUERY("Please enter a valid query!"),
    MISSING_TASKS_FILE("Warning: Unable to find file containing task history. " +
            "Please carry on if this is the first time using Duke."),
    UNABLE_READ_TASK_HISTORY("Warning: Unable to read task history."),
    UNABLE_SAVE_TASK_LIST("Warning: Unable to save task list.");

    private String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
