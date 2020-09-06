package duke.messages;

import java.util.Arrays;
import java.util.List;

import duke.tasks.Task;

/**
 * Contains the relevant responses and messages.
 */
public class Output {

    private static final String INDENT = "    ";

    /**
     * Prompts the user for commands. Initial greeting.
     *
     * @return A string representing the welcome window.
     */
    public String printWelcome() {
        return printGeneralChatWindow(
                Message.MESSAGE_GREETING,
                Message.MESSAGE_WHAT_CAN_I_DO,
                "",
                Message.MESSAGE_HELP_PROMPT);
    }

    /**
     * Prints a chat window showing the list of available commands for the user input.
     *
     * @param commands A collection of commands whose description is to be printed.
     * @return A string representing the list of available commands.
     */
    public String printHelpWindow(String[] commands) {
        return printGeneralChatWindow(commands);
    }

    /**
     * Prints a chat window showing the list of tasks.
     *
     * @param tasks The list of tasks to be printed.
     * @return A string representing the list of tasks.
     */
    public String printTasksChatWindow(List<Task> tasks) {
        StringBuilder result = new StringBuilder();
        result.append(String.format("%s\n", Message.MESSAGE_TASKS_INTRO));

        if (tasks.isEmpty()) {
            result.append(String.format("%s\n", Message.MESSAGE_NO_TASKS_CURRENTLY));
        } else {
            int index = 0;
            for (Task task : tasks) {
                result.append(String.format("%d. %s\n", ++index, task));
            }
        }

        return result.toString();
    }

    /**
     * Prints a chat window that describes the task that is done.
     *
     * @param task The task to be displayed as done.
     * @return A string representing a completed task.
     */
    public String printDoneTaskChatWindow(Task task) {
        return printGeneralChatWindow(Message.MESSAGE_DONE_TASK,
                String.format("%s%s", INDENT, task.toString()));
    }

    /**
     * Prints a chat window that describes the task that is deleted.
     *
     * @param task            The task to be displayed as deleted.
     * @param numOfTotalTasks The number of tasks in the list.
     * @return A string representing a delete task.
     */
    public String printDeleteTaskChatWindow(Task task, int numOfTotalTasks) {
        return printGeneralChatWindow(Message.MESSAGE_DELETE_TASK,
                String.format("%s%s", INDENT, task.toString()),
                printNumberOfTasks(numOfTotalTasks));
    }

    /**
     * Prints a chat window that informs the user that all tasks have been cleared.
     * @return A string representing the clearing of all tasks.
     */
    public String printClearTasksWindow() {
        return Message.MESSAGE_CLEAR_TASKS;
    }

    /**
     * Prints a chat window with a customised description that the task has been added.
     *
     * @param task            The task to be displayed as added.
     * @param numOfTotalTasks The number of tasks in the list.
     * @return A string representing an added task.
     */
    public String printAddTaskChatWindow(Task task, int numOfTotalTasks) {
        return printGeneralChatWindow(Message.MESSAGE_ADD_TASK,
                String.format("%s%s", INDENT, task.toString()),
                printNumberOfTasks(numOfTotalTasks));
    }

    /**
     * Prints a chat window with a list of tasks matching the user input's keyword.
     *
     * @param tasks The list of matching tasks.
     * @return A string representing the list of matching tasks.
     */
    public String printFindTaskChatWindow(List<Task> tasks) {
        StringBuilder result = new StringBuilder();
        result.append(printGeneralChatWindow(Message.MESSAGE_FIND_TASKS_INTRO));

        if (tasks.isEmpty()) {
            result.append(Message.MESSAGE_NO_MATCHING_TASKS);
        } else {
            int index = 0;
            for (Task task : tasks) {
                result.append(printGeneralChatWindow(
                        String.format("%d. %s", ++index, task)));
            }
        }

        return result.toString();
    }

    /**
     * Prints a chat window with a list of tasks scheduled on a particular date sorted chronologically.
     *
     * @param tasks The list of scheduled tasks on that dya.
     * @return A string representing the list of scheduled tasks.
     */
    public String printScheduleChatWindow(List<Task> tasks) {
        StringBuilder result = new StringBuilder();
        result.append(printGeneralChatWindow(Message.MESSAGE_SCHEDULE_INTRO));

        if (tasks.isEmpty()) {
            result.append(Message.MESSAGE_NO_SCHEDULED_TASKS);
        } else {
            int index = 0;
            for (Task task : tasks) {
                result.append(printGeneralChatWindow(
                        String.format("%d. %s", ++index, task)));
            }
        }

        return result.toString();
    }

    /**
     * Prints a goodbye chat window.
     * @return A string representing the goodbye chat window.
     */
    public String printGoodbye() {
        return printGeneralChatWindow(Message.MESSAGE_THANK_YOU, Message.MESSAGE_GOODBYE);
    }

    /**
     * Prints an indented chat window with a customised message.
     *
     * @param messages A series of strings representing the customised message.
     * @return A string representing a series of messages for the user to see.
     */
    public String printGeneralChatWindow(String... messages) {
        StringBuilder result = new StringBuilder();

        Arrays.asList(messages).forEach(m -> result.append(String.format("%s\n", m)));

        return result.toString();
    }

    // Prints the number of tasks left in the list
    private String printNumberOfTasks(int n) {
        return String.format(Message.MESSAGE_NUMBER_OF_TASKS, n);
    }

}
