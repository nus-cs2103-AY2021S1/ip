import java.util.PriorityQueue;

/**
 * Defines a UI class, that handles any UI in Duke.
 *
 * @author Kai Chao
 * @version 1.0
 * @since 26-08-2020
 */
public class Ui {

    /** Divides each command call. */
    private static final String LINE_BREAK = "==========================================================";

    /**
     * Provides the welcome logo and how to start.
     *
     * @return The welcome string contents.
     */
    public String printWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n" + logo + "\n" + "Hello! I'm Duke\n"
                + "What can I do for you today? (type: \"help\" to view list of commands)\n"
                + LINE_BREAK;
    }

    /**
     * Provides line break string.
     *
     * @return The line break string.
     */
    public String printLineBreak() {
        return LINE_BREAK;
    }

    /**
     * Provides good-bye string.
     *
     * @return The good-bye string.
     */
    public String printGoodbye() {
        return "Duke says: Goodbye and have a nice day! :D";
    }

    /**
     * Provides the available input commands and their respective formatting requirements.
     *
     * @return The help string.
     */
    public String printHelp() {
        return "list: displays a sequential view of past inputs\n"
                + "find <task description>: finds all the tasks matched\n"
                + "sortdes: sorts the tasks in alphabetical order\n"
                + "done <task number>: denotes a task as done by checking it\n"
                + "delete <task number>: deletes an existing task\n"
                + "deadline <description> /by <YYYY-MM-DD> <HH:MM>: adds a deadline with "
                + "desired date/time\n"
                + "event <description> /at <YYYY-MM-DD> <HH:MM>: adds an event with "
                + "desired date/time\n"
                + "todo <description>: adds a todo task\n"
                + "bye: terminates program";
    }

    /**
     * Provides a string when no past tasks is found.
     *
     * @return The expected string content.
     */
    public String printNoPastTasks() {
        return "Duke says: No past tasks found";
    }

    /**
     * Provides the list of stored tasks in hard drive in sequential order.
     *
     * @param taskList The current tasks in hard drive.
     * @return The list of past tasks in string.
     */
    public String printPastTasks(TaskList taskList) {
        String toReturn = "Here are your tasks:\n";
        for (int i = 1; i <= taskList.getNoOfTasks(); i++) {
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
    public String printTaskIsDone(Task task) {
        return "Duke says: Good Job! I've marked this task as done:\n" + task;
    }

    /**
     * Provides the message denoting an invalid task number error.
     *
     * @return The string that prompts a valid task number input.
     */
    public String printInvalidTaskNumber() {
        return "Duke says: Please try again with a valid task number";
    }

    /**
     * Provides the message denoting a successful deletion of task.
     *
     * @param removedTask The deleted task.
     * @param remaining The number of remaining tasks.
     * @return The string denoting a successful deletion and the task involved.
     */
    public String printSuccessfulDelete(Task removedTask, int remaining) {
        return "Successfully deleted the task!\n" + removedTask + "\n"
                + printRemainingTasks(remaining);
    }

    /**
     * Provides the number of remaining tasks in string.
     *
     * @return The expected string content.
     */
    public String printRemainingTasks(int remaining) {
        return "You now have " + remaining + " task(s) in your list";
    }

    /**
     * Provides the message denoting a task being added.
     *
     * @param task The added task.
     * @param remaining The number of remaining tasks.
     * @return The string denoting a successful addition and the task involved.
     */
    public String printTasksAdded(Task task, int remaining) {
        return "Duke added into your task list:\n" + task + "\n" + printRemainingTasks(remaining);
    }

    /**
     * Provides a string message from the exception.
     *
     * @param exception The exception encountered.
     * @return The expected exception message.
     */
    public String printErrorMessage(Exception exception) {
        return exception.getMessage();
    }

    /**
     * Provides the list of found tasks in sequential order.
     *
     * @param taskList The list of tasks in the hard drive.
     * @param desToFind The string used to search for the relevant tasks.
     * @return The string of found tasks.
     */
    public String printFoundTasks(TaskList taskList, String desToFind) {
        int number = 1;
        boolean hasResults = false;
        String toReturn = "";
        for (int i = 1; i <= taskList.getNoOfTasks(); i++) {
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

    /**
     * Provides the sorted tasks in the form of string.
     *
     * @param sortedTasks The string sorted based on type.
     * @param size The number of tasks.
     * @return The formatted string message.
     */
    public String printSortByDes(PriorityQueue<Task> sortedTasks, int size) {
        if (size == 0) {
            return "Sorry there are no matching results :(";
        } else {
            String toReturn = "Here are your tasks sorted by date and time:\n";
            for (int i = 1; i <= size; i++) {
                toReturn += i + ".  " + sortedTasks.poll() + "\n";
            }
            return toReturn + "Note that if you wish to change any task, refer to the "
                    + "original list by calling the \"list\" command.";
        }
    }
}
