package duke;

import duke.task.Task;

import java.util.List;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 * Also prints the relevant responses and messages.
 */
public class Ui {
    private static final String logo = "     ___   ____    __    ____  _______     _______.  ______   .___  ___.  _______          ______\n"
            + "    /   \\  \\   \\  /  \\  /   / |   ____|   /       | /  __  \\  |   \\/   | |   ____|        /  __  \\\n"
            + "   /  ^  \\  \\   \\/    \\/   /  |  |__     |   (----`|  |  |  | |  \\  /  | |  |__    ______|  |  |  |\n"
            + "  /  /_\\  \\  \\            /   |   __|     \\   \\    |  |  |  | |  |\\/|  | |   __|  |______|  |  |  |\n"
            + " /  _____  \\  \\    /\\    /    |  |____.----)   |   |  `--'  | |  |  |  | |  |____        |  `--'  |\n"
            + "/__/     \\__\\  \\__/  \\__/     |_______|_______/     \\______/  |__|  |__| |_______|        \\______/\n";
    private static final String indent = "    ";
    private static final String doubleIndent = indent + indent;
    private static final String border = "_________________________________________________________";
    private final Scanner scanner;

    /**
     * Class constructor.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prompts the user for commands. Initial greeting.
     */
    public void printWelcome() {
        printLogo();
        printBorder();
        printGeneralChatWindow("Greetings! I'm Awesome-O.", "What can I do for you?");
        printBorder();
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
     * Prints a chat window showing the list of tasks.
     *
     * @param tasks The list of tasks to be printed.
     */
    public void printTasksChatWindow(List<Task> tasks) {
        printIndentedMessage("Here are the tasks in your list:");

        if (tasks.isEmpty()) {
            printIndentedMessage("No tasks currently");
        } else {
            int index = 0;
            for (Task task : tasks) {
                System.out.printf("%s%d. %s\n", indent, ++index, task);
            }
        }
    }

    /**
     * Prints a chat window that describes the task that is done.
     *
     * @param task The task to be displayed as done.
     */
    public void printDoneTaskChatWindow(Task task) {
        printIndentedMessage("Great! I've marked this task as done:");
        printDoubleIndentedMessage(task.toString());
    }

    /**
     * Prints a chat window that describes the task that is deleted.
     *
     * @param task            The task to be displayed as deleted.
     * @param numOfTotalTasks The number of tasks in the list.
     */
    public void printDeleteTaskChatWindow(Task task, int numOfTotalTasks) {
        printIndentedMessage("Okay. I've removed this task:");
        printDoubleIndentedMessage(task.toString());
        printNumberOfTasks(numOfTotalTasks);
    }

    /**
     * Prints a chat window with a customised description that the task has been added.
     *
     * @param task            The task to be displayed as added.
     * @param numOfTotalTasks The number of tasks in the list.
     */
    public void printAddTaskChatWindow(Task task, int numOfTotalTasks) {
        printIndentedMessage("Alright. I've added this task:");
        printDoubleIndentedMessage(task.toString());
        printNumberOfTasks(numOfTotalTasks);
    }

    /**
     * Prints a goodbye chat window.
     */
    public void printGoodbye() {
        printGeneralChatWindow("Thank you for talking to Awesome-O.", "Have a nice day. Goodbye!");
    }

    /**
     * Prints an indented chat window with a customised message.
     *
     * @param messages A series of strings representing the customised message.
     */
    public void printGeneralChatWindow(String... messages) {
        for (String message : messages) {
            printIndentedMessage(message);
        }
    }

    /**
     * Prints a border for the chat window.
     */
    public void printBorder() {
        System.out.printf("%s%s\n", indent, border);
    }

    /**
     * Prints the Duke logo ("AWESOME-O").
     */
    public void printLogo() {
        System.out.println(logo);
    }

    // Prints an indented generic message
    private void printIndentedMessage(String s) {
        System.out.printf("%s%s\n", indent, s);
    }

    // Prints a double-indented generic message
    private void printDoubleIndentedMessage(String s) {
        System.out.printf("%s%s\n", doubleIndent, s);
    }

    // Prints the number of tasks left in the list
    private void printNumberOfTasks(int n) {
        System.out.printf("%sNow you have %d tasks in the list.\n", indent, n);
    }
}
