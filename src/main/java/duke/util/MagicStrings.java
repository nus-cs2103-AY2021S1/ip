package duke.util;

public class MagicStrings {
    // Error messages
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

    // UI response messages and headers
    public static final String MESSAGE_WELCOME = "Hello! I'm Clara! :D How may I help you? :)\n";
    public static final String MESSAGE_GOODBYE = "Bye! Have a great day and hope to see you soon! :D\n";
    public static final String HEADER_TASK_FIND = "These are the tasks that you are looking for.:)\n";
    public static final String HEADER_TASK_ALL = "These are the tasks in your list. Jiayous! :)\n";
    public static final String HEADER_TASK_NONE = "You have no task in your list. :D\n";
    public static final String HEADER_TASK_ADD = "Okay! I've added this task:";
    public static final String HEADER_TASK_DONE = "Nice! I've marked this task as done:\n\t\t";
    public static final String HEADER_TASK_DELETE = "Okay! I've removed this task:\n\t\t";
}
