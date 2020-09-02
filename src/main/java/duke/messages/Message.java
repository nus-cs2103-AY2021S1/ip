package duke.messages;

/**
 * Contains default messages to send to the user.
 */
public class Message {

    // TODO: change logo
    public static final String LOGO = "     ___   ____    __    ____  _______     _______."
            + "  ______   .___  ___.  _______          ______\n"
            + "    /   \\  \\   \\  /  \\  /   / |   ____| "
            + "/       | /  __  \\  |   \\/   | |   ____|        /  __  \\\n"
            + "   /  ^  \\  \\   \\/    \\/   /  |  |__    "
            + "|   (----`|  |  |  | |  \\  /  | |  |__    ______|  |  |  |\n"
            + "  /  /_\\  \\  \\            /   |   __|    "
            + "\\   \\    |  |  |  | |  |\\/|  | |   __|  |______|  |  |  |\n"
            + " /  _____  \\  \\    /\\    /    |  |____.--"
            + "--)   |   |  `--'  | |  |  |  | |  |____        |  `--'  |\n"
            + "/__/     \\__\\  \\__/  \\__/     |_______|_"
            + "______/     \\______/  |__|  |__| |_______|        \\______/\n";
    public static final String MESSAGE_GREETING = "Greetings! I'm Awesome-O.";
    public static final String MESSAGE_WHAT_CAN_I_DO = "What can I do for you?";
    public static final String MESSAGE_HELP_PROMPT = "...PSST! Type \"help\" for more information!";
    public static final String MESSAGE_TASKS_INTRO = "Here are the tasks in your list:";
    public static final String MESSAGE_NO_TASKS_CURRENTLY = "No tasks currently";
    public static final String MESSAGE_DONE_TASK = "Great! I've marked this task as done:";
    public static final String MESSAGE_DELETE_TASK = "Okay. I've removed this task:";
    public static final String MESSAGE_CLEAR_TASKS = "All tasks have been cleared!";
    public static final String MESSAGE_ADD_TASK = "Alright. I've added this task:";
    public static final String MESSAGE_FIND_TASKS_INTRO = "Here are the tasks that match the keyword:";
    public static final String MESSAGE_NO_MATCHING_TASKS = "No matching tasks!";
    public static final String MESSAGE_THANK_YOU = "Thank you for talking to Awesome-O.";
    public static final String MESSAGE_GOODBYE = "Have a nice day. Goodbye!";
    public static final String MESSAGE_NUMBER_OF_TASKS = "Now you have %d tasks in the list.";

    public static final String ERROR = "ERROR";
    public static final String ERROR_FILE_NOT_FOUND = "ERROR: File to load cannot be found";
    public static final String ERROR_DEADLINE_TIME = "ERROR: Usage: <deadline> <description> /by <time>";
    public static final String ERROR_EVENT_TIME = "ERROR: Usage: <event> <description> /at <time>";
    public static final String ERROR_TIME_FORMATTING = "Time formatting: dd-MM-yyyy HH:mm";
    public static final String ERROR_INVALID_INDEX = "ERROR: Invalid list number input!";
    public static final String ERROR_INVALID_KEYWORD = "ERROR: The keyword cannot be empty!";
    public static final String ERROR_INVALID_TASK_DESCRIPTION = "ERROR: The description of a task cannot be empty!";
    public static final String ERROR_INVALID_TASK_TIME = "ERROR: Please specify a correct date/time for this task!";
    public static final String ERROR_LOADING_ERROR = "ERROR: Loading error";
    public static final String ERROR_TASK_ERROR = "ERROR: Task error";
    public static final String ERROR_UNKNOWN_INPUT = "ERROR: Unknown input! Try again.";

}
