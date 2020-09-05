package duke;

/**
 * A class of exceptions customized for the user inputs and file inputs.
 */
public class DukeException extends Exception {

    /** A DukeException thrown when user input not recognized by the Parser. */
    public static final DukeException INVALID_COMMAND_EXCEPTION = new DukeException(
            "I'm sorry, but I don't know what that means :-(");

    /** A DukeException thrown when a search query is empty. */
    public static final DukeException INVALID_QUERY_EXCEPTION = new DukeException(
            "I'm sorry, what was it you wanted me to find?");

    /**
     * A DukeException thrown when the integer expected after 'done' or 'delete'
     * is missing, or cannot be parsed due to incorrect formatting in the user input.
     */
    public static final DukeException INVALID_TASK_INDEX_EXCEPTION = new DukeException(
            "Sorry, that is not a valid task.");

    /** A DukeException thrown when user inputs an empty task. */
    public static final DukeException INVALID_TASK_DESCRIPTION_EXCEPTION = new DukeException(
            "Sorry, the description of a task cannot be empty.");

    /** A DukeException thrown when user inputs a deadline with an incorrect format. */
    public static final DukeException INVALID_DEADLINE_FORMAT_EXCEPTION = new DukeException(
            "Sorry, the description of a Deadline must be in this format:\n"
            + "\tdeadline [task name] /by [deadline]");

    /** A DukeException thrown when user inputs an event with an incorrect format. */
    public static final DukeException INVALID_EVENT_FORMAT_EXCEPTION = new DukeException(
            "Sorry, the description of an Event must be in this format:\n"
            + "\tevent [task name] /at [time]");

    /** A DukeException thrown when a line in the specified file cannot be parsed. */
    public static final DukeException FILE_PARSING_EXCEPTION = new DukeException(
            "Sorry, an error occurred while trying to parse the file.");

    /**
     * A DukeException thrown when an I/O error occurs while trying to load or create a file at the
     * specified filepath.
     */
    public static final DukeException FILE_LOADING_EXCEPTION = new DukeException(
            "Sorry, the specified file does not seem to exist.");

    /**
     * Sole constructor. Used for creating static instances of DukeException;
     * Restricted to within the class; non-instantiable otherwise.
     * @param message A custom message tagging the DukeException, detailing the error.
     */
    private DukeException(String message) {
        super(message);
    }
}
