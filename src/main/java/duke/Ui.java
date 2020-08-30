package duke;

import java.util.List;
import java.util.Scanner;

import duke.task.Task;

/**
 * Deals with interactions with the user.
 * Also prints the relevant responses and messages.
 */
public class Ui {

    private static final String logo = "     ___   ____    __    ____  _______     _______."
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
    private static final String indent = "    ";
    private static final String doubleIndent = indent + indent;
    private static final String border = "_________________________________________________________";
    private static Scanner scanner;

    /**
     * Class constructor.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prompts the user for commands. Initial greeting.
     *
     * @return A string representing the welcome window.
     */
    public String printWelcome() {
        return printGeneralChatWindow(printLogo(),
                "Greetings! I'm Awesome-O.", "What can I do for you?",
                "",
                "...PSST! Type \"help\" for more information!");
    }

    /**
     * Reads the user input.
     *
     * @return A string representing the user input.
     */
    public String readInput() {
        return scanner.nextLine();
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
        result.append("Here are the tasks in your list:\n");

        if (tasks.isEmpty()) {
            result.append("No tasks currently\n");
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
        return printGeneralChatWindow("Great! I've marked this task as done:",
                String.format("%s%s", indent, task.toString()));
    }

    /**
     * Prints a chat window that describes the task that is deleted.
     *
     * @param task            The task to be displayed as deleted.
     * @param numOfTotalTasks The number of tasks in the list.
     * @return A string representing a delete task.
     */
    public String printDeleteTaskChatWindow(Task task, int numOfTotalTasks) {
        return printGeneralChatWindow("Okay. I've removed this task:",
                String.format("%s%s", indent, task.toString()),
                printNumberOfTasks(numOfTotalTasks));
    }

    /**
     * Prints a chat window that informs the user that all tasks have been cleared.
     * @return A string representing the clearing of all tasks.
     */
    public String printClearTasksWindow() {
        return "All tasks have been cleared!";
    }

    /**
     * Prints a chat window with a customised description that the task has been added.
     *
     * @param task            The task to be displayed as added.
     * @param numOfTotalTasks The number of tasks in the list.
     * @return A string representing an added task.
     */
    public String printAddTaskChatWindow(Task task, int numOfTotalTasks) {
        return printGeneralChatWindow("Alright. I've added this task:",
                String.format("%s%s", indent, task.toString()),
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
        result.append(printGeneralChatWindow("Here are the tasks that match the keyword:"));

        if (tasks.isEmpty()) {
            result.append("No matching tasks!");
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
        return printGeneralChatWindow("Thank you for talking to Awesome-O.", "Have a nice day. Goodbye!");
    }

    /**
     * Prints an indented chat window with a customised message.
     *
     * @param messages A series of strings representing the customised message.
     * @return A string representing a series of messages for the user to see.
     */
    public String printGeneralChatWindow(String... messages) {
        StringBuilder result = new StringBuilder();

        for (String message : messages) {
            result.append(String.format("%s\n", message));
        }

        return result.toString();
    }

    /**
     * Prints a border for the chat window.
     *
     * @return A string representing the border for the chat window.
     */
    public String printBorder() {
        return border;
    }

    /**
     * Prints the Duke logo ("AWESOME-O").
     *
     * @return A string representing the Duke logo.
     */
    public String printLogo() {
        return logo;
    }

    // Prints the number of tasks left in the list
    private String printNumberOfTasks(int n) {
        return String.format("Now you have %d tasks in the list.", n);
    }

    // Prints a newline character
    private String printNewline() {
        return "\n";
    }

}
