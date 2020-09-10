public class DukeException extends Exception {
    /** Error message of the Exception to be printed. */
    private final String errorMessage;

    /**
     * Constructs new Exception object based on error type.
     *
     * @param errorType Description of error.
     */
    public DukeException(String errorType) {
        String message;
        switch (errorType) {
        case "emptyList":
            message = "You don't have any tasks currently!";
            break;
        case "invalidCommand":
            message = "OOPS!!! I'm sorry, but I don't know what that means :-(";
            break;
        case "invalidDone":
            message = "OOPS!! Please indicate existing task numbers separated by commas!";
            break;
        case "invalidTodo":
            message = "OOPS!!! The description of a todo cannot be empty.";
            break;
        case "invalidDeadlineTask":
            message = "OOPS!!! The deadline task is formatted wrongly.";
            break;
        case "invalidDeadlineDateTime":
            message = "OOPS!!! The deadline datetime should be in a valid 'YYYY-MM-DD HH:MM' format.";
            break;
        case "invalidEventChronology":
            message = "OOPS!!! The event end time should be after start time.";
            break;
        case "invalidEvent":
            message = "OOPS!!! The event task is formatted wrongly.";
            break;
        case "invalidEventDateTime":
            message = "OOPS!!! The event datetime should be in a valid 'YYYY-MM-DD HH:MM to YYYY-MM-DD HH:MM' format.";
            break;
        case "invalidDelete":
            message = "OOPS!!! Please indicate existing task numbers separated by commas!";
            break;
        case "invalidFind":
            message = "OOPS!!! You cannot find tasks with no keywords!";
            break;
        case "noMatchingTasks":
            message = "OOPS!! I couldn't find any tasks matching your keyword!";
            break;
        default:
            message = "Unknown error. Contact the developer NOW!.";
        }
        this.errorMessage = message;
    }

    /**
     * Prints out the error message of the Exception.
     *
     * @return Error message.
     */
    public String print() {
        return this.errorMessage;
    }
}
