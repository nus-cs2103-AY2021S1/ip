package fei.exception;

/**
 * This class stores most of the possible errors in Alison.
 */
public class FeiException extends Exception {

    public FeiException(String msg) {
        super(msg);
    }

    public static FeiException defaultException() {
        return new FeiException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static FeiException operationException() {
        return new FeiException("OOPS!!! You must provided the index of the task after this operation.\n"
               + "(i.e. done/delete 3)");
    }

    public static FeiException emptyDescriptionException() {
        return new FeiException("OOPS!!! The description of a task cannot be empty.");
    }

    public static FeiException invalidIndexException() {
        return new FeiException("OOPS!!! You entered an invalid index for this operation.");
    }

    public static FeiException deadlineException() {
        return new FeiException("OOPS!!! You must provide a date "
                + "after '/by' for a deadline. \n"
                + "(i.e. deadline return book /by 2020-01-01)");
    }

    public static FeiException eventException() {
        return new FeiException("OOPS!!! You must provide a time interval "
                + "after '/at' for an event. \n"
                + "(i.e. event project meeting /at Mon 2-4pm)");
    }

    public static FeiException deadlineParseException() {
        return new FeiException("Sorry, I can't parse the time format of your input. \n"
                + "I will continue store the deadline with your input.\n"
                + "\nIf you prefer a more readable format,"
                + " try using 'yyyy-mm-dd' after '/by' (e.g. 2020-01-01).\n");
    }

    public static FeiException loadingException() {
        return new FeiException("Loading error. Creating new task list file for you!");
    }

    public static FeiException writingException() {
        return new FeiException("Something went wrong during writing process. The saved file might not be correct.");
    }

}
