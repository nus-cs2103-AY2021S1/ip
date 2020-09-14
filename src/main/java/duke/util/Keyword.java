package duke.util;

/**
 * Contains all keywords being used in the entire app.
 */
public class Keyword {

    // Task class
    public static final String TICK_SYMBOL = "[\u2713]";
    public static final String CROSS_SYMBOL = "[\u2718]";
    public static final String DONE = "Done";
    public static final String NOT_DONE = "Not done";

    // Deadline class
    public static final String DEADLINE_SYMBOL = "[D]";
    public static final String DEADLINE_BY = " (by: ";
    public static final String KEYWORD_DEADLINE = "DEADLINE";

    // Event class
    public static final String EVENT_SYMBOL = "[E]";
    public static final String EVENT_AT = " (at: ";
    public static final String KEYWORD_EVENT = "EVENT";

    // ToDo class
    public static final String TODO_SYMBOL = "[T]";
    public static final String KEYWORD_TODO = "TODO";

    // Storage
    public static final String CSV_HEADER = "Task type,Description,"
        + "Time frame (for Event),Time created or Deadline,Status\n";
    public static final String BASE_DIRECTORY = "user.dir";
    public static final String FOLDER_NAME = "/data";
    public static final String FILE_NAME = "/tasklist.csv";

    // MISC
    public static final String CLOSE_BRACKET = ")";
    public static final String NIL = "-";
    public static final String SINGLE_SPACE = " ";
    public static final String LINE_SEPARATOR = "\n";
    public static final String EMPTY_STRING = "";
    public static final int TIMEOUT_DURATION = 600;

    // Parser
    public static final String KEYWORD_LIST = "LIST";
    public static final String KEYWORD_DONE = "DONE";
    public static final String KEYWORD_DELETE = "DELETE";
    public static final String KEYWORD_FIND = "FIND";
    public static final String KEYWORD_BYE = "BYE";
    public static final String KEYWORD_HELP = "HELP";
    public static final String KEYWORD_SORT = "SORT";
    public static final String EVENT_DELIMITER = " /at";
    public static final String DEADLINE_DELIMITER = " /by";
    public static final String INVALID_TASK_TYPE = "Invalid task type!";
    public static final String INVALID_ARR_ERROR = "Array is not empty";
    public static final String MULTI_SPACE = "\\s{2,}";
    public static final int ARRAY_SIZE = 2;

    // Ui
    public static final String EMPTY_STRING_ERROR = "String builder is empty!";
    public static final String WELCOME_MESSAGE_ONE = "Welcome to TheDuke!";
    public static final String WELCOME_MESSAGE_TWO = "What can I do for you?";
    public static final String GOODBYE_MESSAGE = "Bye! Hope to see you again soon!";
    public static final String TASK_MARKED_MESSAGE = "Nice! I've marked this task as done:";
    public static final String TASK_DELETED_MESSAGE = "Noted. I've removed this task:";
    public static final String FOUR_SPACES = "    ";
    public static final String NUM_OF_TASKS_MESSAGE = "Now you have %d tasks in the list.";
    public static final String TASK_ADDED_MESSAGE = "Got it. I've added this task:";
    public static final String EMPTY_TASK_LIST_MESSAGE = "You currently have no tasks in the list.";
    public static final String DISPLAY_TASKS_MESSAGE = "Here are the %stasks in your list:";
    public static final String NO_MATCHING_TASKS_MESSAGE = "There are no matching tasks with the keyword %s.";
    public static final String NUM_FORMATTER = "%d. %s";
    public static final String FILE_CREATION_ERR = "Error in creating file!";
    public static final String SORT_SUCCESS_MESSAGE = "Your list has been sorted!";
    public static final String SORT_PROMPT_MESSAGE = "Type in 'list' to see the sorted list.";

    // DateTimeParser
    public static final String DATE_TIME_FORMAT = "MMM d yyyy / h.mm a";
    public static final String TIME_SYMBOL = "T";

    // CsvConverter
    public static final String CSV_SEPARATOR = "  ,";
    public static final String CSV_FORMAT = "%s  ,%s  ,%s  ,%s  ,%s\n";
}
