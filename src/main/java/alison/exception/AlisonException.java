package alison.exception;

/**
 * This class stores most of the possible errors in Alison.
 */
public class AlisonException extends Exception {

    public AlisonException(String msg) {
        super(msg);
    }

    public static AlisonException defaultException() {
        return new AlisonException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static AlisonException operationException() {
        return new AlisonException("OOPS!!! You must provided the index of the task after this operation.\n" +
                "(i.e. done/delete 3)");
    }

    public static AlisonException emptyDescriptionException() {
        return new AlisonException("OOPS!!! The description of a task cannot be empty.");
    }

    public static AlisonException invalidIndexException() {
        return new AlisonException("OOPS!!! You entered an invalid index for this operation.");
    }

    public static AlisonException deadlineException() {
        return new AlisonException("OOPS!!! You must provide a date " +
                "after '/by' for a deadline. \n" +
                "(i.e. deadline return book /by 2020-01-01)");
    }

    public static AlisonException eventException() {
        return new AlisonException("OOPS!!! You must provide a time interval " +
                "after '/at' for an event. \n" +
                "(i.e. event project meeting /at Mon 2-4pm)");
    }

    public static AlisonException deadlineParseException() {
        return new AlisonException("Sorry, I can't parse the time format you just inputted. \n" +
                "Try it in this format: yyyy-mm-dd (e.g. 2020-01-01).");
    }

    public static AlisonException loadingException() {
        return new AlisonException("Loading error. Creating new task list file for you!");
    }

    public static AlisonException writingException() {
        return new AlisonException("Something went wrong during writing process. The saved file might not be correct.");
    }

    public static AlisonException findException() {
        return new AlisonException("Sorry. You can only input one keyword for searching.");
    }
}
