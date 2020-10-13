package duke.exception;

public class DukeException extends Throwable {

    //for exceptions
    private static final String delete_task_number_missing = "Please enter a valid duke.task number";
    private static final String TASK_INDEX_OUT_OF_BOUNDS = "That task does not exist.";
    private static final String TASK_NUMBER_FORMAT = "Please enter the task number in numerals.";
    private static final String TASK_NO_DESCRIPTION = "Invalid, there is missing information.";
    private static final String TASK_INVALID_TYPE = "Invalid, not an accepted task type.";
    private static final String TASK_INVALID_DATE = "Invalid date. Please enter the date in YYYY-MM-DD format.";
    private static final String TASK_INVALID_PRIORITY = "Invalid priority. "
        + "Please enter either 'high', 'medium' or 'low'.";
    private String word;
    private ExceptionType type;

    public enum ExceptionType {
        UNEXPECTED_INPUT_IN_FILE,
        NO_DESCRIPTION_PROVIDED,
        INVALID_NUMBER_INPUT,
        NO_TIMEFRAME_PROVIDED,
        INVALID_TIMEFRAME,
        REQUESTED_NONEXISTENT_ITEM,
        INVALID_PRIORITY
    }

    public DukeException(ExceptionType type) {
        this.type = type;
    }

    public DukeException(String word, ExceptionType type) {
        this.word = word;
        this.type = type;
    }

    @Override
    public String toString() {
        switch (type) {
        case INVALID_TIMEFRAME:
            return TASK_INVALID_DATE;
        case INVALID_NUMBER_INPUT:
            return TASK_NUMBER_FORMAT;
        case NO_TIMEFRAME_PROVIDED:
            return "This task requires a date. Please follow the following format: "
                    + "\ndeadline [description] /by [date]"
                    + "\nOR:"
                    + "\nevent [description] /at [date]";
        case NO_DESCRIPTION_PROVIDED:
            return TASK_NO_DESCRIPTION;
        case UNEXPECTED_INPUT_IN_FILE:
            return TASK_INVALID_TYPE;
        case REQUESTED_NONEXISTENT_ITEM:
            return TASK_INDEX_OUT_OF_BOUNDS;
        case INVALID_PRIORITY:
            return TASK_INVALID_PRIORITY;
        default:
            return "There is an error. Try again.";
        }
    }
}
