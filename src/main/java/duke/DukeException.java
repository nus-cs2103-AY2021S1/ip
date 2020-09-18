package duke;

/**
 * This class stores most of the possible errors in Alison.
 */
public class DukeException extends Exception {

    public DukeException(String msg) {
        super(msg);
    }

    public static DukeException noResponseException() {
        return new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static DukeException operationException() {
        return new DukeException("OOPS!!! You must provided the index of the task after this operation.\n"
                + "(i.e. delete 3)");
    }

    public static DukeException missingConnectorException() {
        return new DukeException("OOPS!!! You need to enter /by or /at after a deadline or event task.");
    }

    public static DukeException emptyDescriptionException() {
        return new DukeException("OOPS!!! The description of a task cannot be empty.");
    }

    public static DukeException invalidIndexException() {
        return new DukeException("OOPS!!! You entered an invalid index for this operation.");
    }

    public static DukeException dateException() {
        return new DukeException("OOPS! You must provide a date "
                + "after '/by' or '/at' for a deadline or event task. \n"
                + "(i.e. deadline ip/by 2020-08-19)");
    }


    public static DukeException deadlineParseException() {
        return new DukeException("Sorry, try using 'yyyy-mm-dd' after '/by or /at' (e.g. 2020-01-01).\n");
    }

}
