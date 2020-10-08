package duke.utils;

public class Messages {

    public static final String MESSAGE_WELCOME = "Hello from\n"
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String MESSAGE_BYE = "\t Bye. Hope to see you again soon!";
    public static final String MESSAGE_HELP = "\t Here are the available commands:\n"
            + "\t \u2022 Create a todo (eg todo <task>)\n"
            + "\t \u2022 Create an event (eg event <eventName> /at <date/time(s)>)\n"
            + "\t \u2022 Create a deadline (eg deadline <deadlineName> /by <date/time>\n"
            + "\t \u2022 List all tasks (eg list)\n"
            + "\t \u2022 Delete a task (eg delete <task index>\n"
            + "\t \u2022 Mark a task as done (eg done <task index>\n"
            + "\t \u2022 Find a task (eg find <search words>)\n"
            + "\t \u2022 List today's task (eg today)\n"
            + "\t \u2022 Confirm a date/time (eg confirm <task index> <date/time index>\n"
            + "\t \u2022 Exit the program (eg bye)\n";
    public static final String MESSAGE_GREETING = "\t Hello! I'm Duke\n" + MESSAGE_HELP;
    public static final String MESSAGE_ADD_TASK = "\t Got it. I've added this task:\n";
    public static final String MESSAGE_DELETE_TASK = "\t Noted. I've removed this task:\n";
    public static final String MESSAGE_DONE_TASK = "\t Nice! I've marked this task as done:\n";
    public static final String MESSAGE_FIND_NO_MATCH = "\t There are no tasks that matches your search word.";
    public static final String MESSAGE_FIND = "\t Here are the matching tasks in your list:\n";
    public static final String MESSAGE_LIST = "\t Here are the tasks in your list:\n";
    public static final String MESSAGE_TODAY = "\t Here are your tasks today:\n";
    public static final String MESSAGE_CONFIRM = "\t Nice! I've confirmed the date for this event:\n";

    public static final String MESSAGE_INVALID_DATE_TIME = "OOPS! Invalid date / time format!";
    public static final String MESSAGE_EMPTY_DUE_DATE = "OOPS! The due date of deadline cannot be empty!";
    public static final String MESSAGE_EMPTY_EVENT_DATE = "OOPS! The date / time of event cannot be empty!";
    public static final String MESSAGE_EMPTY_SEARCH_WORD = "OOPS! Your search word cannot be empty.";
    public static final String MESSAGE_EMPTY_TASK_DELETED = "OOPS! Task deleted cannot be empty!";
    public static final String MESSAGE_EMPTY_TASK_DESCRIPTION = "OOPS! The description of a %s cannot be empty.";
    public static final String MESSAGE_EMPTY_TASK_DONE = "OOPS! Task done cannot be empty!";
    public static final String MESSAGE_INVALID_COMMAND = "OOPS! I'm sorry but I don't know what that means :-(";
    public static final String MESSAGE_INVALID_FILE_PATH = "OOPS! File paths must end with .txt";
    public static final String MESSAGE_INVALID_TASK = "OOPS! Invalid task found.";
    public static final String MESSAGE_NO_SUCH_TASK = "OOPS! No such task exists!";
    public static final String MESSAGE_STORAGE_EXCEPTION = "OOPS! Error saving file.";
    public static final String MESSAGE_TASKLIST_TRANSLATOR_EXCEPTION = "OOPS! Error reading from file.";
    public static final String MESSAGE_NO_SUCH_TENTATIVE_DATE_EXCEPTION = "OOPS! No such tentative date exists!";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT_EXCEPTION = "OOPS! The command format is wrong!";

    public static final String MESSAGE_HANDLED_INVALID_COMMAND_ASSERTION =
            "Invalid command scenario has been handled earlier.";
    public static final String MESSAGE_HANDLED_INVALID_FILEPATH_ASSERTION =
            "A non-existent file scenario has been handled earlier.";


}
