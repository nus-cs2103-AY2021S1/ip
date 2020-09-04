package duke.ui;

import duke.task.TaskList;

public class Message {
    public static final String MESSAGE_WELCOME = "¡Hola! Soy Dora!\nWhat can do for my amigo today?";
    public static final String MESSAGE_EXIT = "Adiós amigo! Till next time!";
    public static final String MESSAGE_ADDED = "¡Está bien! I've added this task:\n    ";
    public static final String MESSAGE_DONE = "¡Fantástico! I've marked this task as done:\n    ";
    public static final String MESSAGE_DELETE = "Entiendo! I've removed this task:\n    ";
    public static final String MESSAGE_FIND = "Here are the matching tasks in your list:\n";
    public static final String MESSAGE_NO_TASK = "There are currently no tasks in your list\n";
    public static final String MESSAGE_LIST = "Here are the tasks in your list:\n";
    public static final String MESSAGE_LOADING_ERROR = "Failed to load tasks. An empty list is created.\n";

    /**
     * Prints the total number of tasks in the task list.
     *
     * @param taskList The task list containing all the tasks.
     * @return A message of the total number of tasks in the task list.
     */
    public static String getTotalTaskMessage(TaskList taskList) {
        return "Now you have " + taskList.size()
                + (taskList.size() == 1 ? " task " : " tasks ")
                + "in the list\n";
    }

    public static String showError(String message) {
        return String.format("☹ Oh, no!!! Mi amigo, %s\n", message);
    }

    public static String concatLines(String... str) {
        return String.join("", str);
    }
}
