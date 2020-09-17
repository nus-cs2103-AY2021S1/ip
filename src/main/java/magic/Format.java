package magic;

public class Format {

    //Storage format
    public static final String DATA = "%s\n";

    //Task format
    public static final String LOCAL_DATE = "LLL dd uuuu";
    public static final String DISPLAY_DEADLINE = "[%s]%s (by: %s) %s";
    public static final String DISPLAY_EVENT = "[%s]%s (at: %s) %s";
    public static final String DISPLAY_TASK = "[%c] %s";
    public static final String DISPLAY_TODO = "[%s]%s %s";
    public static final String DISPLAY_TASK_AS_LIST = "%d. %s\n";

    //UI headers format
    public static final String ADD_TASK_HEADER =
            "Go it. I've added this task:\n%s\n%s";
    public static final String DONE_TASK_HEADER =
            "Nice! I've marked this task as done:\n%s";
    public static final String REMOVE_TASK_HEADER =
            "Noted. I've removed this task:\n%s\n%s";
    public static final String TOTAL_TASK_HEADER_EMPTY =
            "TaskList is empty! Please add a task first.";
    public static final String TOTAL_TASK_HEADER =
            "Now you have %d %s in the list.";
    public static final String FIND_TASK_HEADER =
            "Here are the matching tasks in your list:\n%s";
    public static final String FIND_TASK_HEADER_EMPTY =
            "No matching tasks found";
    public static final String TAGGED_TASK_HEADER =
            "Noted. The following task has been tagged!\n%s";
    public static final String LIST_TASK_HEADER_EMPTY =
            "List is empty!";
    public static final String LIST_TASK_HEADER =
            "Here are the tasks in your list:\n%s";

}
