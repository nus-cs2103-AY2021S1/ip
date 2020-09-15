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
    public static final String WELCOME_MESSAGE = "Welcome back";
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
    public static final String SORT_PROMPT_MESSAGE = "Type in 'list' to see the new ordering.";

    // Commands
    public static final String KEYWORD_MATCHING = "matching ";

    // Help command
    public static final String HEADER = "Here are the available commands that I know:";
    public static final String[] COMMAND_LIST = new String[]{
        "todo _ (e.g. todo 3)",
        "deadline 'task name' /by 'end time' (e.g. deadline Exercise /by 23-8-20)",
        "event 'task name' /at 'start time - end time' (e.g. meeting /at Sunday 2pm - 4pm)",
        "list",
        "done _ (e.g. done 4)",
        "delete _ (e.g. delete 4)",
        "find '   ' (e.g. find book)",
        "sort",
        "bye"
    };

    // DateTimeParser
    public static final String DATE_TIME_OUTPUT_FORMAT = "d MMM yyyy @ h.mm a";
    public static final String DATE_TIME_INPUT_FORMAT = "d-M-yy HHmm";
    public static final String DATE_INPUT_FORMAT = "d-M-yy";
    public static final String TIME_INPUT_FORMAT = "HHmm";

    // CsvConverter
    public static final String CSV_SEPARATOR = "  ,";
    public static final String CSV_FORMAT = "%s  ,%s  ,%s  ,%s  ,%s\n";
    public static final String KEYWORD_NOT_DONE = "NOT DONE";

    // Exception
    public static final String INVALID_DATE_ERR_ONE = "Date is poorly formatted!";
    public static final String INVALID_DATE_ERR_TWO = "Time should be in either of these 3 formats:";
    public static final String OOPS_MESSAGE = "OOPS!!! %s";
    public static final String DUPLICATE_TASK_MESSAGE = "This task already exists in the list!";
    public static final String EMPTY_FIND_MESSAGE = "The query word cannot be empty!";
    public static final String EMPTY_TIME_MESSAGE = "Time of %s task is not specified!";
    public static final String EMPTY_TASK_DESCRIPTION_MESSAGE = "The description of a todo cannot be empty.";
    public static final String FILE_UPDATE_FAIL_MESSAGE = "Error in updating .csv file in storage.";
    public static final String INVALID_DEADLINE_ERR_ONE = "Deadline task is poorly formatted.";
    public static final String INVALID_DEADLINE_ERR_TWO = "Here is a proper format: deadline 'task name' /by d-M-yy";
    public static final String INVALID_DEADLINE_ERR_THREE = "e.g. deadline Exercise /by 23-8-20";
    public static final String INVALID_EVENT_ERR_ONE = "Event task is poorly formatted.";
    public static final String INVALID_EVENT_ERR_TWO = "Here is a proper format: event 'event name'"
        + " /at 'start time to end time'";
    public static final String INVALID_EVENT_ERR_THREE = "e.g. meeting /at Sunday 2 - 4pm";
    public static final String FILE_FORMAT_ERR = "CSV file is poorly formatted!";
    public static final String INVALID_TASK_NUM_ONE = "Task number does not exist in the list.";
    public static final String INVALID_TASK_NUM_TWO = "Your current list only has %d tasks!";
    public static final String INVALID_SIMPLE_COMMAND_ONE = "%s format is invalid.";
    public static final String INVALID_SIMPLE_COMMAND_TWO = "Please try again with a proper format like '%s 3'";
    public static final String TASK_ALREADY_DONE_MESSAGE = "Task has already been mark as done!";
    public static final String UNKNOWN_COMMAND_ONE = "I'm sorry, but I don't know what that means.";
    public static final String UNKNOWN_COMMAND_TWO = "Type 'help' to display the list of commands available.";

}
