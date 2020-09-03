package duke.util;

public class Strings {
    public static final String ERROR_TODO_DESCRIPTION_EMPTY =
            "\tSorry! The description of a todo cannot be empty :')";
    public static final String ERROR_DEADLINE_FORMAT_INCORRECT =
            "\tPaise! :') Please use the format: deadline <task> /by <time>"
            + "\n\t\t*time format: <yyyy-mm-dd> or  <yyyy-mm-dd HH:mm>";
    public static final String ERROR_EVENT_FORMAT_INCORRECT =
            "\tPaise! :') Please use the correct format: event <task> /at <time> +"
                    + "\n\t\t*time format: <yyyy-mm-dd> or  <yyyy-mm-dd HH:mm>";
    public static final String ERROR_INDEX_OUT_OF_BOUNDS =
            "Sorry! The index is out of bounds! :')";
    public static final String ERROR_DONE_FORMAT_INCORRECT =
            "\tPaise! :') Please use the correct format: done <order of task in the list>";
    public static final String ERROR_DELETE_FORMAT_INCORRECT =
            "\tPaise! :') Please use the correct format: delete <order of task in the list>";
    public static final String ERROR_COMMAND_FORMAT_INCORRECT =
            "\tApologies! I do not understand what that means :')";
}
