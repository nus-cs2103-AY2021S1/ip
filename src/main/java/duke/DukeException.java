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
            "Sorry, the file does not seem to exist.\n"
            + "Try: CREATE <filepath>.");

    /**
     * A DukeException thrown when there is an existing file in the filepath where the new file is to be created.
     */
    public static final DukeException FILE_OVERWRITE_EXCEPTION = new DukeException(
            "Sorry, that filepath already has an existing file.\n"
                    + "Would you like to load that file, or create a file in a different filepath?");

    /**
     * A DukeException thrown when an I/O error occurs while trying to write to a file at the
     * specified filepath.
     */
    public static final DukeException FILE_NO_ACCESS_EXCEPTION = new DukeException(
            "Sorry, I cannot seem to access the file");

    /**
     * A DukeException thrown when the user commands Duke to load/create a file but does not specify the filepath.
     */
    public static final DukeException EMPTY_FILEPATH_EXCEPTION = new DukeException(
            "Sorry, you did not enter a filepath.");

    public static final DukeException LOADING_CAPACITY_EXCEPTION = new DukeException(
            "Sorry, you cannot create or load another file while working with the current file.\n"
                    + "Please restart the session if you would like to change files.");

    /**
     * Sole constructor. Used for creating static instances of DukeException;
     * Restricted to within the class; non-instantiable otherwise.
     *
     * @param message A custom message tagging the DukeException, detailing the error.
     */
    private DukeException(String message) {
        super(message);
    }
}
