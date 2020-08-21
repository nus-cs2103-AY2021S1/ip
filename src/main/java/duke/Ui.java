package duke;

import duke.task.Task;

import java.util.List;
import java.util.Scanner;

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

    public Ui() {
        scanner = new Scanner(System.in);
    }

    // Initial greeting, prompt user for commands
    public void printWelcome() {
        printLogo();
        printBorder();
        printGeneralChatWindow("Greetings! I'm Awesome-O.", "What can I do for you?");
        printBorder();
    }

    // Reads user input
    public String readInput() {
        return scanner.nextLine();
    }

    // Prints a chat window showing the list of tasks
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

    // Prints a chat window that describes a task that is done
    public void printDoneTaskChatWindow(Task task) {
        printIndentedMessage("Great! I've marked this task as done:");
        printDoubleIndentedMessage(task.toString());
    }

    // Prints a chat window that describes a task that is deleted
    public void printDeleteTaskChatWindow(Task task, int numOfTotalTasks) {
        printIndentedMessage("Okay. I've removed this task:");
        printDoubleIndentedMessage(task.toString());
        printNumberOfTasks(numOfTotalTasks);
    }

    // Prints a chat window with a customised "add task" description
    public void printAddTaskChatWindow(Task task, int numOfTotalTasks) {
        printIndentedMessage("Alright. I've added this task:");
        printDoubleIndentedMessage(task.toString());
        printNumberOfTasks(numOfTotalTasks);
    }

    // Print goodbye chat window
    public void printGoodbye() {
        printGeneralChatWindow("Thank you for talking to Awesome-O.", "Have a nice day. Goodbye!");
    }

    // Prints an indented chat window with a customised message
    public void printGeneralChatWindow(String... messages) {
        for (String message : messages) {
            printIndentedMessage(message);
        }
    }

    // Prints a border as in the chat window
    public void printBorder() {
        System.out.printf("%s%s\n", indent, border);
    }

    // Prints Duke logo
    public void printLogo() {
        System.out.println(logo);
    }

    // Prints a box with the symbol inside, i.e. [s]
    private String box(String symbol) {
        return String.format("[%s]", symbol);
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
