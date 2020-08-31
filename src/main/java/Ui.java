/**
 * Defines a UI class, that handles any UI in Duke.
 *
 * @author Kai Chao
 * @version 1.0
 * @since 26-08-2020
 */
public class Ui {

    /** Divides each command call. */
    static final String LINE_BREAK = "==========================================================";

    /**
     * Provides the welcome logo and how to start.
     */
    public String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n" + logo + "\n" + "Hello! I'm Duke\n"
                + "What can I do for you today? (type: \"help\" to view list of commands)\n"
                + LINE_BREAK;
    }

    public String showLineBreak() {
        return LINE_BREAK;
    }

    public String showGoodbye() {
        return "Duke says: Goodbye and have a nice day! :D";
    }

    /**
     * Provides the available input commands and their respective formatting requirements.
     *
     * @return The help string.
     */
    public String showHelp() {
        return "list: displays a sequential view of past inputs\n"
                + "find <task description>: finds all the tasks matched\n"
                + "done <task number>: denotes a task as done by checking it\n"
                + "delete <task number>: deletes an existing task\n"
                + "deadline <description> /by <YYYY-MM-DD> <HH:MM>: adds a deadline with "
                + "desired date/time\n"
                + "event <description> /at <YYYY-MM-DD> <HH:MM>: adds an event with "
                + "desired date/time\n"
                + "todo <description>: adds a todo task\n"
                + "bye: terminates program";
    }

    public String showNoPastTasks() {
        return "Duke says: No past tasks found";
    }

    /**
     * Provides the list of stored tasks in hard drive in sequential order.
     *
     * @param taskList The current tasks in hard drive.
     * @return The list of past tasks in string.
     */
    public String showPastTasks(TaskList taskList) {
        String toReturn = "Here are your tasks:\n";
        for (int i = 1; i <= taskList.noOfTasks(); i++) {
            toReturn += i + ".  " + taskList.getTask(i - 1) + "\n";
        }
        return toReturn + "If you wish to mark a task as completed, input: done <task number>";
    }

    /**
     * Provides the message denoting a task is done.
     *
     * @param task The done task.
     * @return The string denoting a successful deletion and the task involved.
     */
    public String showTaskIsDone(Task task) {
        return "Duke says: Good Job! I've marked this task as done:\n" + task;
    }

    /**
     * Provides the message denoting an invalid task number error.
     *
     * @return The string that prompts a valid task number input.
     */
    public String showInvalidTaskNumber() {
        return "Duke says: Please try again with a valid task number";
    }

    /**
     * Provides the message denoting a successful deletion of task.
     *
     * @param removedTask The deleted task.
     * @param remaining The number of remaining tasks.
     * @return The string denoting a successful deletion and the task involved.
     */
    public String showSuccessfulDelete(Task removedTask, int remaining) {
        return "Successfully deleted the task!\n" + removedTask + "\n"
                + showRemainingTasks(remaining);
    }

    public String showRemainingTasks(int remaining) {
        return "You now have " + remaining + " task(s) in your list";
    }

    /**
     * Provides the message denoting a task being added.
     *
     * @param task The added task.
     * @param remaining The number of remaining tasks.
     * @return The string denoting a successful addition and the task involved.
     */
    public String showTasksAdded(Task task, int remaining) {
        return "Duke added into your task list:\n" + task + "\n" + showRemainingTasks(remaining);
    }

    public String showErrorMessage(String message) {
        return message;
    }

    /**
     * Provides the list of found tasks in sequential order.
     *
     * @param taskList The list of tasks in the hard drive.
     * @param desToFind The string used to search for the relevant tasks.
     * @return The string of found tasks.
     */
    public String showFoundTasks(TaskList taskList, String desToFind) {
        int number = 1;
        boolean hasResults = false;
        String toReturn = "";
        for (int i = 1; i <= taskList.noOfTasks(); i++) {
            if (taskList.getTask(i - 1).description.contains(desToFind)) {
                if (!hasResults) {
                    toReturn += "Here are your tasks:\n";
                }
                toReturn += number + ".  " + taskList.getTask(i - 1) + "\n";
                number++;
                hasResults = true;
            }
        }
        if (hasResults) {
            toReturn += "If you wish to mark a task as completed, input: done <task number>";
        } else {
            toReturn += "Sorry there are no matching results :(";
        }
        return toReturn;
    }

}
