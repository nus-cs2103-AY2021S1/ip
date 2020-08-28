package dd.exception;

/**
 * A Duke exception thrown when an exception specifically to the Duke system occurs.
 */
public class DukeException extends Exception {

    private String message;

    /**
     * Class Constructor with specified error message.
     *
     * @param errorMessage Specified error message for exception.
     */
    public DukeException(String errorMessage) {
        message = errorMessage;
    }

    /**
     * Class Constructor.
     */
    public DukeException() {

    }

    /**
     * Returns error message.
     *
     * @return Error message stored in exception.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Creates a new exception for empty TaskList found for date given in check command.
     *
     * @param date Date given in check command.
     * @return A new DukeException containing no task found message.
     */
    public DukeException emptyCheckDate(String date) {
        String msg = "No tasks found on " + date + "!";

        return new DukeException(msg);
    }

    /**
     * Creates a new exception for empty TaskList found for description given in find command.
     *
     * @param desc Description given in find command.
     * @return A new DukeException containing no task found message.
     */
    public DukeException emptyCheckDesc(String desc) {
        String msg = "No tasks related to " + desc + "!";

        return new DukeException(msg);
    }

    /**
     * Creates a new exception for invalid date given in check command.
     *
     * @return A new DukeException containing invalid date message.
     */
    public DukeException invalidCheckDate() {
        String msg = "I don't understand :( Please input date as DD-MM-YYYY\n"
                + "Example: 31-12-2020";

        return new DukeException(msg);
    }

    /**
     * Creates a new exception for invalid date given in event or deadline commands.
     *
     * @return A new DukeException containing invalid date message.
     */
    public DukeException invalidDate() {
        String msg = "I don't understand :( Please input date as DD-MM-YYYY or DD-MM-YYYY HHmm\n"
                + "Example: 31-12-2020 or 31-12-2020 2359";

        return new DukeException(msg);
    }

    /**
     * Creates a new exception for invalid date given in deadline command.
     *
     * @return A new DukeException containing invalid date message.
     */
    public DukeException invalidDeadline() {
        String msg = "Due date not detected, try again!\n"
                + "Please input deadline as 'deadline (title) /by (date)'\n"
                + "Example: deadline return book /by 31-12-2020";

        return new DukeException(msg);
    }

    /**
     * Creates a new exception for invalid date given in event command.
     *
     * @return A new DukeException containing invalid date message.
     */
    public DukeException invalidEvent() {
        String msg = "Event date not detected, try again!\n"
                + "Please input event as 'event (title) /at (date)'\n"
                + "Example: event group meeting /at 31-12-2020";

        return new DukeException(msg);
    }

    /**
     * Creates a new exception for invalid task number given.
     *
     * @return A new DukeException containing invalid task number message.
     */
    public DukeException invalidTaskNumber() {
        String msg = "hmm.. I don't think thats a valid task, try again?";

        return new DukeException(msg);
    }

    /**
     * Creates a new exception for no data written to file.
     *
     * @return A new DukeException containing no data written message.
     */
    public DukeException noData() {
        String msg = "No data written to file.";

        return new DukeException(msg);
    }
}
