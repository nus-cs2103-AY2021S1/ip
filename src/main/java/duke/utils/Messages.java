package duke.utils;

public class Messages {
    public static final String WELCOME_MESSAGE = "Hello! I'm Duke, your personal assistant\n"
            + "How may I be of assistance?\n"
            + "If you are unsure of any command,"
            + " just type \"help\" and a list of all the commands and its uses will be shown.";
    public static final String FAREWELL_MESSAGE = "Bye. Hope my service has been satisfactory."
            + "Hope to see you again soon.";

    public static final String CORRECT_TODO_COMMAND = "-Todo: This command is used to create a new Todo task.\n"
            + "The correct command is:\n \"todo <task description>.\"";
    public static final String CORRECT_DEADLINE_COMMAND = "-Deadline: This command is used to create a new "
            + "deadline task.\n"
            + "The correct command is:\n \"deadline <task description> /by <datetime>\"";
    public static final String CORRECT_EVENT_COMMAND = "- Event: This command is used to create a new Event task.\n"
            + "The correct command is:\n \"event <task description> /at <datetime>\"";
    public static final String CORRECT_DONE_COMMAND = "- Done: This command is used to mark an existing task as done.\n"
            + "The correct command is:\n \"done <task index>\"";
    public static final String CORRECT_DELETE_COMMAND = "- Delete: This command is used to delete an existing task.\n"
            + "The correct command is:\n \"delete <task index>\"";
    public static final String CORRECT_FIND_COMMAND = "- Find: This command is used to find tasks that fit a keyword.\n"
            + "The correct command is:\n \"find <keyword>\"";
    public static final String CORRECT_LIST_COMMAND = "- List: This command is used to list all existing tasks.\n"
            + "The correct command is:\n \"list\"";
    public static final String CORRECT_SET_TAG_COMMAND = "- Tag: This command is used to tag tasks.\n"
            + "The correct command is:\n \"tag <task index> #<tag name>\"";
    public static final String CORRECT_OVERDUE_COMMAND = "- Overdue: This command is used to display all overdue "
            + "uncompleted tasks.\n"
            + "The correct command is:\n \"overdue\"";
    public static final String CORRECT_UNCOMPLETED_COMMAND = "- Uncompleted: This command is used to display all "
            + " uncompleted tasks.\n"
            + "The correct command is:\n \"uncompleted\"";
    public static final String CORRECT_COMPLETED_COMMAND = "- Completed: This command is used to display all "
            + " completed tasks.\n"
            + "The correct command is:\n \"completed\"";
    public static final String CORRECT_BYE_COMMAND = "- Bye: This command will terminate the program.\n"
            + "The correct command is:\n \"bye\"";

    public static final String INVALID_COMMAND_INPUT_MESSAGE = "OOPS!!!! You have inputted an invalid command.\n"
            + "The only commands I know are:\n"
            + "1) todo <task description>\n"
            + "2) deadline <task description> /by <datetime>\n"
            + "3) event <task description> /at <datetime>\n"
            + "4) done <task index>\n"
            + "5) delete <task index>\n"
            + "6) find <keyword>\n"
            + "7) list\n"
            + "8) uncompleted\n"
            + "9) completed\n"
            + "10) tag <task index> #<tag name>\n"
            + "11) today\n"
            + "12) overdue\n"
            + "13) bye";
    public static final String INVALID_TASK_INDEX_ERROR_MESSAGE = "Invalid task index! Please choose another index.";
    public static final String INVALID_TODO_COMMAND = "OOPS!!! The description of a todo cannot be empty!\n"
            + "The correct command is:\ntodo <task description>";
    public static final String INVALID_DEADLINE_COMMAND = "OOPS!!! The description of a deadline cannot be empty.\n"
            + "The correct command is:\ndeadline <task description> /by <datetime>";
    public static final String INVALID_EVENT_COMMAND = "OOPS!!! The description of an event cannot be empty.\n"
            + "The correct command is:\nevent <task description> /at <datetime>";
    public static final String INVALID_FIND_COMMAND = "OOPS!!! You are missing a keyword.\n"
            + "The correct command is:\nfind <keyword>";
    public static final String INVALID_TODAY_COMMAND = "OOPS!!! Invalid Today Command.\n"
            + "The correct command is:\ntoday";
    public static final String INVALID_UNCOMPLETED_COMMAND = "OOPS!!! Invalid Uncompleted Command.\n"
            + "The correct command is:\nuncompleted";
    public static final String INVALID_COMPLETED_COMMAND = "OOPS!! Invalid Completed Command.\n"
            + "The correct command is:\ncompleted";
    public static final String INVALID_OVERDUE_COMMAND = "OOPS!! Invalid Overdue Command.\n"
            + "The correct command is:\noverdue";
    public static final String INVALID_HELP_COMMAND = "OOPS!! Invalid Help Command.\n"
            + "The correct command is:\nhelp";
    public static final String INVALID_TAG_COMMAND = "OOPS! Invalid Tag Command.\n"
            + "The correct command is:\ntag <task index> #<tag name>";
    public static final String INVALID_TAG_FORMAT_COMMAND = "Oh no! Looks like you have inputted the wrong format."
            + "The correct command should be:\ntag <task index> #<tag name>";
    public static final String INVALID_DATE_TIME_FORMAT = "OOPS!! You have inputted an incorrect date and time format";
    public static final String INVALID_COMMAND_ASSERTIONS = "Invalid command has been handled earlier";
    public static final String MISSING_DATE_AND_TIME_FOR_DEADLINE = "OOPS!!! Missing date/time.\n"
            + "Correct command is:\ndeadline <task description> /by <datetime>";
    public static final String MISSING_DATE_AND_TIME_FOR_EVENT = "OOPS!!! Missing date/time.\n"
            + "Correct command is:\nevent <task description> /at <datetime>";

    public static final String TASK_MARKED_AS_DONE_MESSAGE = "Good job on completing this task!"
            + "I have marked this task as done: \n";
    public static final String FILTERED_TASKS_STARTING_MESSAGE = "Here are the matching tasks in your list:\n";
    public static final String NO_TASKS_UNDER_KEYWORD_MESSAGE = "I am sorry but there are no tasks "
            + "under the keyword you have inputted.";
    public static final String NO_UNCOMPLETED_TASKS_MESSAGE = "You do not have any uncompleted tasks."
            + " Great job on completing all your tasks";
    public static final String NO_COMPLETED_TASKS_MESSAGE = "You do not have any completed tasks."
            + " You better get started on completing some tasks soon";
    public static final String NO_OVERDUE_TASKS_MESSAGE = "There are no overdue tasks."
            + " Good job on keeping up with your tasks";
    public static final String NO_TASKS_FOR_TODAY_MESSAGE = "There are no tasks for today."
            + " Go ahead and chill for the day";
    public static final String NO_TASKS_MESSAGE = "Looks like there are no tasks currently."
            + " Go ahead and start creating new tasks";


    public static final String HELP_MESSAGE = "Here are the commands you can use:\n"
            + "1) " + CORRECT_TODO_COMMAND + "\n"
            + "2) " + CORRECT_DEADLINE_COMMAND + "\n"
            + "3) " + CORRECT_EVENT_COMMAND + "\n"
            + "4) " + CORRECT_DONE_COMMAND + "\n"
            + "5) " + CORRECT_DELETE_COMMAND + "\n"
            + "6) " + CORRECT_FIND_COMMAND + "\n"
            + "7) " + CORRECT_LIST_COMMAND + "\n"
            + "8) " + CORRECT_SET_TAG_COMMAND + "\n"
            + "9) " + CORRECT_OVERDUE_COMMAND + "\n"
            + "10) " + CORRECT_COMPLETED_COMMAND + "\n"
            + "11) " + CORRECT_UNCOMPLETED_COMMAND + "\n"
            + "12) " + CORRECT_BYE_COMMAND;
}
