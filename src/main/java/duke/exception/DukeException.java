package duke.exception;

/**
 * This class is responsible for making Exceptions made specifically for the purpose of the Duke Chatbot.
 * These Exceptions are particularly targeted at improper input provided by the users and give a custom message
 * based on what the error is.
 */
public class DukeException extends Exception {
    protected static final String HORIZONTAL_RULE = "____________________________________________________________";


    /**
     * Initialises a DukeException object with the message that is passed in the argument
     *
     * @param message Message that will be used to generate the DukeException
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Generates a DukeException when an invalid input is provided by the user
     *
     * @return DukeException object signifying a bad command passed by the user
     */
    public static DukeException badCommand() {
        return new DukeBadCommandException();
    }

    /**
     * Generates a DukeException when an invalid input is provided for the Todo command
     *
     * @return DukeException object signifying a bad input is provided for the ToDo command
     */
    public static DukeException badToDo() {
        return new DukeBadTodoException();
    }

    /**
     * Generates a DukeException when an invalid task input is provided for the Deadline command
     *
     * @return DukeException object signifying a bad task input is provided for the Deadline command
     */
    public static DukeException badDeadlineTask() {
        return new DukeBadDeadlineTaskException();
    }

    /**
     * Generates a DukeException when an invalid deadline input is provided for the Deadline command
     *
     * @return DukeException object signifying a bad deadline input is provided for the Deadline command
     */
    public static DukeException badDeadlineDate() {
        return new DukeBadDeadlineDateException();
    }

    /**
     * Generates a DukeException when an invalid task input is provided for the Event command
     *
     * @return DukeException object signifying a bad task input is provided for the Event command
     */
    public static DukeException badEventTask() {
        return new DukeBadEventTaskException();
    }

    /**
     * Generates a DukeException when an invalid date input is provided for the Event command
     *
     * @return DukeException object signifying a bad date input is provided for the Event command
     */
    public static DukeException badEventDate() {
        return new DukeBadEventDateException();
    }

    /**
     * Generates a DukeException when an invalid find request is provided for the Find command
     *
     * @return DukeException object signifying a bad find request for Find command
     */
    public static DukeException badFind() {
        return new DukeBadFindException();
    }

    /**
     * Generates a DukeException when an Undo command is given with no previous undoable commands
     * @return DukeException object signifying no undoable commands have been given
     */
    public static DukeException badUndoException() {
        return new DukeBadUndoException();
    }

}
