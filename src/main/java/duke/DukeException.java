package duke;

/**
 * This class is responsible for making Exceptions made specifically for the purpose of the Duke Chatbot.
 * These Exceptions are particularly targeted at improper input provided by the users and give a custom message
 * based on what the error is.
 */
public class DukeException extends Exception {
    private static final String HORIZONTAL_RULE = "____________________________________________________________";
    private static final String BAD_COMMAND_ERROR = "I have literally no idea what you're asking of me. \uD83D\uDE20";
    private static final String BAD_TODO_ERROR = "HELLO! Please fill up a description of todo. "
            + "Can't be empty!!! \uD83D\uDE20";
    private static final String BAD_FIND_ERROR = "HELLO! How can I find something if you don't tell me what to find?";
    private static final String BAD_DEADLINE_DATE_ERROR = "HELLO! Please fill up due date for the deadline. "
            + "Can't be empty!!! \uD83D\uDE20";
    private static final String BAD_DEADLINE_TASK_ERROR = "HELLO! Please fill up the task for the deadline. "
            + "Can't be empty!!! \uD83D\uDE20";
    private static final String BAD_EVENT_DATE_ERROR = "HELLO! Please fill up the date for the event. "
            + "Can't be empty!!! \uD83D\uDE20";
    private static final String BAD_EVENT_TASK_ERROR = "HELLO! Please fill up the event details. "
            + "Can't be empty!!! \uD83D\uDE20";

    /**
     * Initialises a DukeException object with the message that is passed in the argument
     * @param message Message that will be used to generate the DukeException
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Generates a DukeException when an invalid input is provided by the user
     * @return DukeException object signifying a bad command passed by the user
     */
    public static DukeException badCommand() {
        return new DukeException(HORIZONTAL_RULE + "\n" + BAD_COMMAND_ERROR + "\n" + HORIZONTAL_RULE);
    }

    /**
     * Generates a DukeException when an invalid input is provided for the Todo command
     * @return DukeException object signifying a bad input is provided for the ToDo command
     */
    public static DukeException badToDo() {
        return new DukeException(HORIZONTAL_RULE + "\n" + BAD_TODO_ERROR + "\n" + HORIZONTAL_RULE);
    }

    /**
     * Generates a DukeException when an invalid task input is provided for the Deadline command
     * @return DukeException object signifying a bad task input is provided for the Deadline command
     */
    public static DukeException badDeadlineTask() {
        return new DukeException(HORIZONTAL_RULE + "\n" + BAD_DEADLINE_TASK_ERROR + "\n" + HORIZONTAL_RULE);
    }

    /**
     * Generates a DukeException when an invalid deadline input is provided for the Deadline command
     * @return DukeException object signifying a bad deadline input is provided for the Deadline command
     */
    public static DukeException badDeadlineDate() {
        return new DukeException(HORIZONTAL_RULE + "\n" + BAD_DEADLINE_DATE_ERROR + "\n" + HORIZONTAL_RULE);
    }

    /**
     * Generates a DukeException when an invalid task input is provided for the Event command
     * @return DukeException object signifying a bad task input is provided for the Event command
     */
    public static DukeException badEventTask() {
        return new DukeException(HORIZONTAL_RULE + "\n" + BAD_EVENT_TASK_ERROR + "\n" + HORIZONTAL_RULE);
    }

    /**
     * Generates a DukeException when an invalid date input is provided for the Event command
     * @return DukeException object signifying a bad date input is provided for the Event command
     */
    public static DukeException badEventDate() {
        return new DukeException(HORIZONTAL_RULE + "\n" + BAD_EVENT_DATE_ERROR + "\n" + HORIZONTAL_RULE);
    }

    public static DukeException badFind() {
        return new DukeException(HORIZONTAL_RULE + "\n" + BAD_FIND_ERROR + "\n" + HORIZONTAL_RULE);
    }

}
