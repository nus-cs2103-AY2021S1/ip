public class DukeException extends Exception {
    public static final DukeException INVALID_COMMAND_EXCEPTION = new DukeException(
            "I'm sorry, but I don't know what that means :-(");

    public static final DukeException INVALID_TASK_INDEX_EXCEPTION = new DukeException(
            "Sorry, that is not a valid task.");

    public static final DukeException INVALID_TASK_DESCRIPTION_EXCEPTION = new DukeException(
            "Sorry, the description of a task cannot be empty.");

    public static final DukeException FILE_PARSING_EXCEPTION = new DukeException(
            "Sorry, an error occurred while trying to parse the file.");

    public static final DukeException FILE_LOADING_EXCEPTION = new DukeException(
            "Sorry, the specified file does not seem to exist.");

    public DukeException(String message) {
        super(message);
    }
}
