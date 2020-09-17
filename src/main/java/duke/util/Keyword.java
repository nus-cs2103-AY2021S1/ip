package duke.util;

/**
 * Class that contains all the magic strings.
 */
public class Keyword {

    // Miscellaneous
    public static final String KEYWORD_TICK = "\u2714";
    public static final String KEYWORD_CROSS = "\u2718";
    public static final String KEYWORD_EXTRA_SPACE = "    ";
    public static final String KEYWORD_ONE_SPACE = " ";
    public static final String KEYWORD_MULTIPLE_SPACE = "  +";
    public static final String KEYWORD_DEADLINE_FORMAT = " /by ";
    public static final String KEYWORD_EVENT_FORMAT = " /at ";
    public static final String KEYWORD_USER = "user";
    public static final String KEYWORD_DUKE = "duke";
    public static final String KEYWORD_EMPTY_MESSAGE = "";
    public static final String KEYWORD_YES = "y";
    public static final String KEYWORD_NO = "n";
    public static final String KEYWORD_STRING_ZERO = "0";

    // Task message
    public static final String KEYWORD_TASK_MARKED_BEFORE = "This task has already been marked!";
    public static final String KEYWORD_TASK_MARKED = "Nice! I've marked this task as done:";
    public static final String KEYWORD_TASK_REMINDER_ON = "The reminder of this task %s, has been activated";
    public static final String KEYWORD_TASK_REMINDER_OFF = "The reminder of this task %s, has been deactivated";

    // UI message
    public static final String KEYWORD_UI_GOODBYE_MESSAGE = "Bye ^.^, Hope to see you again soon!!!";
    public static final String KEYWORD_UI_HELLO_DUKE = "Welcome back";

    // Command
    public static final String KEYWORD_TODO = "todo";
    public static final String KEYWORD_EVENT = "event";
    public static final String KEYWORD_DEADLINE = "deadline";
    public static final String KEYWORD_BYE = "bye";
    public static final String KEYWORD_LIST = "list";
    public static final String KEYWORD_DONE = "done";
    public static final String KEYWORD_DELETE = "delete";
    public static final String KEYWORD_FIND = "find";
    public static final String KEYWORD_HELP = "help";
    public static final String KEYWORD_REMIND = "remind";

    // Notification message
    public static final String KEYWORD_ADD_NOTIFICATION = "Got it. I've added this task:";
    public static final String KEYWORD_DELETE_NOTIFICATION = "Noted. I've removed this task:";
    public static final String KEYWORD_FIND_SUCCESS = "Here are the matching tasks in your list:";
    public static final String KEYWORD_HELP_DISPLAY_MESSAGE = "Here are the list of commands available:\n";
    public static final String KEYWORD_HELP_LIST = "1. list\n";
    public static final String KEYWORD_HELP_BYE = "2. bye\n";
    public static final String KEYWORD_HELP_TODO = "3. todo \'task name\' (e.g. todo task 1)\n";
    public static final String KEYWORD_HELP_DELETE = "4. delete ___ (e.g. delete 1) "
            + " *Note that it should be a value more than 0*\n";
    public static final String KEYWORD_HELP_DONE = "5. done ___ (e.g. done 1)  "
            + "*Note that it should be a value more than 0*\n";
    public static final String KEYWORD_HELP_FIND = "6. find ___ (e.g. find book) "
            + "*Note that only 1 keyword is allowed*\n";
    public static final String KEYWORD_HELP_REMIND = "7. remind _ _ (e.g.remind 1 y)" + "\n" + KEYWORD_EXTRA_SPACE
            + " *Sets reminder on task 1 in task list, y or n represents yes or no respectively*\n";
    public static final String KEYWORD_HELP_DEADLINE = "8. deadline \'task name\' /by \'any date format\' " + "\n"
            + KEYWORD_EXTRA_SPACE + "(e.g. deadline project /by YYYY-MM-DD HHMM or YYYY-MM-DD)\n";
    public static final String KEYWORD_HELP_EVENT = "9. event \'event name\' /at \'any date format\' " + "\n"
            + KEYWORD_EXTRA_SPACE + "(e.g. event project /at YYYY-MM-DD HHMM or YYYY-MM-DD)\n";
    public static final String KEYWORD_LIST_EMPTY_MSG = "Your list is empty!!!";
    public static final String KEYWORD_LIST_SHOW_TASK = "Here are the tasks in your list:";

    // Exception messages
    public static final String KEYWORD_DUPLICATE_EXCEPTION = " ☹ OOPS! This specific task exist before!";
    public static final String KEYWORD_EMPTY_EXCEPTION = " ☹ OOPS! You must fill in the text for %s";
    public static final String KEYWORD_BYE_EXCEPTION = " ☹ OOPS! Did you mean bye? "
            + "(Note: There should not be anything behind bye)";
    public static final String KEYWORD_DATE_EXCEPTION = " ☹ OOPS! A proper date format would be YYYY-MM-DD HHMM "
            + "(e.g. 2019-10-15 1600) or YYYY-MM-DD (e.g. 2019-10-15)";
    public static final String KEYWORD_DEADLINE_EXCEPTION = " ☹ OOPS! A proper deadline format would be like, "
            + "e.g. deadline \'task name\' /by \'YYYY-MM-DD HHMM or YYYY-MM-DD\'";
    public static final String KEYWORD_DELETE_EXCEPTION = " ☹ OOPS! A proper delete format would be like e.g. delete "
            + "\'an integer that is between 1(if list is not empty) to the number of items in the list\'";
    public static final String KEYWORD_DONE_EXCEPTION = " ☹ OOPS! A proper done format would be like  e.g. done "
            + "\'an integer that is between 1(if list is not empty) to the number of items in the list\'";
    public static final String KEYWORD_EVENT_EXCEPTION = " ☹ OOPS! A proper event format would be like, "
            + "e.g. event \'event name\' /at \'YYYY-MM-DD HHMM or YYYY-MM-DD\'";
    public static final String KEYWORD_FIND_EXCEPTION = " ☹ OOPS! A proper find format would be like, "
            + "e.g. find \'keyword\' " + "(Note that only 1 keyword is allowed.)";
    public static final String KEYWORD_HELP_EXCEPTION = " ☹ OOPS! Did you mean help? "
            + "(Note: There should not be anything behind help)";
    public static final String KEYWORD_LIST_EXCEPTION = " ☹ OOPS! Did you mean list? "
            + "(Note: There should not be anything behind list)";
    public static final String KEYWORD_REMINDER_EXCEPTION = " ☹ OOPS! A proper remind format would be like, "
            + "e.g. remind \'index in task list\' \'y or n\' ";
    public static final String KEYWORD_UNKNOWN_COMMAND_EXCEPTION = "☹ OOPS!!! I'm sorry, but I don't know what "
            + "that means :-( Type \"help\" to view the list of commands available";
}
