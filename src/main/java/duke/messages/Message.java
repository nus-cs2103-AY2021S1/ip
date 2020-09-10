package duke.messages;

/**
 * Contains default messages to send to the user.
 */
public class Message {

    public static final String MESSAGE_GREETING = "I AM Awesome-O.";
    public static final String MESSAGE_WHAT_CAN_I_DO = "What can Awesome-O do for you?";
    public static final String MESSAGE_HELP_PROMPT = "...PSST! Type \"help\" for more information!";
    public static final String MESSAGE_TASKS_INTRO = "Awesome-O now shows you your tasks:";
    public static final String MESSAGE_NO_TASKS_CURRENTLY = "No tasks currently";
    public static final String MESSAGE_DONE_TASK = "Awesome-O marked this task as done:";
    public static final String MESSAGE_DELETE_TASK = "Awesome-O removed this task:";
    public static final String MESSAGE_CLEAR_TASKS = "Awesome-O has cleared all tasks.";
    public static final String MESSAGE_ADD_TASK = "Awesome-O added this task:";
    public static final String MESSAGE_FIND_TASKS_INTRO = "Awesome-O shows you the tasks that match the keyword:";
    public static final String MESSAGE_NO_MATCHING_TASKS = "No matching tasks!";
    public static final String MESSAGE_SCHEDULE_INTRO = "Awesome-O shows you the tasks scheduled on this day:";
    public static final String MESSAGE_NO_SCHEDULED_TASKS = "Awesome-O sees no scheduled tasks on this day.";
    public static final String MESSAGE_THANK_YOU = "Awesome-O thanks you for talking to him.";
    public static final String MESSAGE_GOODBYE = "Awesome-O WILL MISS YOU.";
    public static final String MESSAGE_NUMBER_OF_TASKS = "Now you have %d tasks in the list.";

    public static final String ERROR = "ERROR";
    public static final String ERROR_FILE_NOT_FOUND = "ERROR: File to load cannot be found";
    public static final String ERROR_DEADLINE_TIME = "ERROR: Usage: <deadline> <description> /by <time>";
    public static final String ERROR_EVENT_TIME = "ERROR: Usage: <event> <description> /at <time>";
    public static final String ERROR_SCHEDULE_INPUT = "ERROR: Usage: schedule <date>";
    public static final String ERROR_TIME_FORMATTING = "Time formatting: dd-MM-yyyy HH:mm";
    public static final String ERROR_INVALID_INDEX = "ERROR: Invalid list number input!";
    public static final String ERROR_INVALID_KEYWORD = "ERROR: The keyword cannot be empty!";
    public static final String ERROR_DATE_FORMATTING = "Date formatting: dd-MM-yyyy";
    public static final String ERROR_INVALID_TASK_DESCRIPTION = "ERROR: The description of a task cannot be empty!";
    public static final String ERROR_INVALID_TASK_TIME = "ERROR: Please specify a correct date/time for this task!";
    public static final String ERROR_LOADING_ERROR = "ERROR: Loading error";
    public static final String ERROR_TASK_ERROR = "ERROR: Task error";
    public static final String ERROR_UNKNOWN_INPUT = "ERROR: Unknown input! Try again.";

}
