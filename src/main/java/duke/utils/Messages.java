package duke.utils;

public class Messages {
    public static final String WELCOME_MESSAGE = "Hello! I'm Duke\nHow may I be of assistance?\n";
    public static final String FAREWELL_MESSAGE = "Bye. Hope my service has been satisfactory."
           + "Hope to see you again soon.";

    public static final String INVALID_COMMAND_INPUT_MESSAGE = "OOPS!!!! You have inputted an invalid command.\n"
            + "The only commands I know are:\n" + "1) Todo <task description>\n"
            + "2) Deadline <task description> /by <datetime>\n" + "3) Event <task description> /at <datetime>";
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
    public static final String INVALID_UNCOMPLETED_COMMAND = "OOPS!!! Invalid Uncompleted Command. \n"
            + "The correct command is:\nuncompleted";
    public static final String INVALID_COMPLETED_COMMAND = "OOPS!! Invalid Completed Command. \n"
            + "The correct command is:\ncompleted";
    public static final String INVALID_OVERDUE_COMMAND = "OOPS!! Invalid Overdue Command. \n"
            + "The correct command is:\noverdue";
    public static final String MISSING_DATE_AND_TIME_FOR_DEADLINE = "OOPS!!! Missing date/time.\n"
            + "Correct command is:\ndeadline <task description> /by <datetime>";
    public static final String MISSING_DATE_AND_TIME_FOR_EVENT = "OOPS!!! Missing date/time.\n"
            + "Correct command is:\nevent <task description> /at <datetime>";

    public static final String TASK_MARKED_AS_DONE_MESSAGE = "Good job on completing this task!"
            + "I have marked this task as done: \n";
    public static final String NO_TASKS_UNDER_KEYWORD_MESSAGE = "I am sorry but there are no tasks "
            + "under the keyword you have inputted.";



}
