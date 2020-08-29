package duke.Ui;

import duke.Task.TaskList;

public class Message {
    public static final String MESSAGE_WELCOME = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";
    public static final String BORDERS = "___________________________________________________________________________";
    public static final String MESSAGE_ADDED = "Got it. I've added this task:\n    ";
    public static final String MESSAGE_DONE = "Nice! I've marked this task as done:\n   ";
    public static final String MESSAGE_DELETE = "Noted. I've removed this task:\n    ";
    public static final String MESSAGE_FIND = "Here are the matching tasks in your list:";
    public static final String MESSAGE_NO_TASK = "There are currently no tasks in your list";
    public static final String MESSAGE_LIST = "Here are the tasks in your list:";
    public static final String MESSAGE_LOADING_ERROR = "Failed to load tasks. An empty list is created.";

    /**
     * Prints the total number of tasks in the task list.
     *
     * @param taskList The task list containing all the tasks
     * @return A message of the total number of tasks in the task list
     */
    public static String getTotalTaskMessage(TaskList taskList) {
        return "Now you have " + taskList.size()
                + (taskList.size() == 1 ? " task " : " tasks ")
                + "in the list";
    }

    public static String showError(String message) {
        return String.format("☹ OOPS!!! %s\n", message);
    }
}
