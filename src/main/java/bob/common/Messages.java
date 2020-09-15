package bob.common;

/**
 * Contains messages used in Bob.
 */
public class Messages {
    public static final String INTRO = "Welcome Boss, what can Bob do for you?";
    public static final String OUTRO = "Good bye boss, see you soon.";
    public static final String ADD_MSG = "Yes boss, I have added this task to your list:\n";
    public static final String DONE_MSG = "I have marked the task as done, good job boss.\n";
    public static final String REMINDER_HEADER = "============= Reminders =============\n";
    public static final String NO_UNFINISHED_TASKS_REMINDER = "You have no unfinished tasks.\n";
    public static final String REMINDER_MSG = "You have these unfinished task / upcoming events & deadlines boss.\n";
    public static final String DELETE_MSG = "I have deleted the task.\n";
    public static final String UNDO_MSG = "Yes boss, I have undone your command.\n";
    public static final String INVALID_COMMAND =
            "Sorry boss, I am not smart enough to understand that. Please give me a valid instruction.\n";
    public static final String EMPTY_TASK =
            "Sorry boss, I can't guess your task that well. Please enter a description for your task.\n";
    public static final String INVALID_DATE_AND_TIME =
            "Sorry boss, I don't recognize your date and time. Please enter a valid date"
                    + " and time in this format \"YY/MM/DD HHmm\" after \"/by\" or \"/at\".\n"
                    + "For example, \"deadline return book /by 2020/08/23 1930\"."
                    + " Or, \"event birthday /at 2020/12/09 0000\".\n";
    public static final String INVALID_NUMBER =
            "Sorry boss but there is no valid task number. Please enter a valid task number.\n";
    public static final String EMPTY_FIND =
            "I don't know what you want me to find boss. :( Please enter a description for me to search.\n";
    public static final String LOADING_ERROR =
            "Saved file containing your task list could not be load, so I have created a new task list for you.\n";
    public static final String UPDATE_ERROR =
            "Update to saved file has failed, file reader might be corrupted. :(\n";
    public static final String INVALID_PATHNAME =
            "Couldn't recognise the file name. Please restart Bob with a different file path name.\n";
    public static final String INVALID_UNDO_MSG = "Sorry boss, you have no commands to undo.";
}
