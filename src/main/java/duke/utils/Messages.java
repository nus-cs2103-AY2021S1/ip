package duke.utils;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_INIT_FAILED = "Failed to initialise task list application. Exiting...";
    public static final String MESSAGE_WELCOME = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_TASK_LISTED_OVERVIEW_FORMAT = "Now you have %1$d tasks in the list.";
    public static final String MESSAGE_STATISTICS = "You have completed %1$d out of %2$d tasks.\nKeep it up!!";

    public static final String MESSAGE_INVALID_COMMAND = "Unknown command! Type 'help' if you are lost.\n";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_BLANK_STRING = "Invalid format! Type something after the command.";
    public static final String MESSAGE_INVALID_TASK_DISPLAYED_INDEX = "The task index provided is invalid.";
    public static final String MESSAGE_INVALID_DATE_TIME = "Invalid date and time format! "
            + "It should be in this format: [DD/MM/YYYY HHmm].\n"
            + "\tExample: 12/12/2020 0945";

}
