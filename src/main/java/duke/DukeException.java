package duke;

public class DukeException extends Exception {
    public static final DukeException INVALID_COMMAND_EXCEPTION = new DukeException(
            "I'm sorry, but I don't know what that means :-(");

    /** A DukeException thrown when a search query is empty. */
    public static final DukeException INVALID_QUERY_EXCEPTION = new DukeException(
            "I'm sorry, what was it you wanted me to find?");

    public static final DukeException INVALID_TASK_INDEX_EXCEPTION = new DukeException(
            "Sorry, that is not a valid task.");

    public static final DukeException INVALID_TASK_DESCRIPTION_EXCEPTION = new DukeException(
            "Sorry, the description of a task cannot be empty.");

    public static final DukeException INVALID_DEADLINE_FORMAT_EXCEPTION = new DukeException(
            "Sorry, the description of a Deadline must be in this format:\n" +
                    "\tdeadline [task name] /by [deadline]");

    public static final DukeException INVALID_EVENT_FORMAT_EXCEPTION = new DukeException(
            "Sorry, the description of an Event must be in this format:\n" +
                    "\tevent [task name] /at [time]");

    public static final DukeException FILE_PARSING_EXCEPTION = new DukeException(
            "Sorry, an error occurred while trying to parse the file.");

    public static final DukeException FILE_LOADING_EXCEPTION = new DukeException(
            "Sorry, the specified file does not seem to exist.");

    public DukeException(String message) {
        super(message);
    }
}
