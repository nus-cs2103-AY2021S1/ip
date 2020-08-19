import java.util.List;

public class Printer {
    private static final String logo = "     ___   ____    __    ____  _______     _______.  ______   .___  ___.  _______          ______\n"
            + "    /   \\  \\   \\  /  \\  /   / |   ____|   /       | /  __  \\  |   \\/   | |   ____|        /  __  \\\n"
            + "   /  ^  \\  \\   \\/    \\/   /  |  |__     |   (----`|  |  |  | |  \\  /  | |  |__    ______|  |  |  |\n"
            + "  /  /_\\  \\  \\            /   |   __|     \\   \\    |  |  |  | |  |\\/|  | |   __|  |______|  |  |  |\n"
            + " /  _____  \\  \\    /\\    /    |  |____.----)   |   |  `--'  | |  |  |  | |  |____        |  `--'  |\n"
            + "/__/     \\__\\  \\__/  \\__/     |_______|_______/     \\______/  |__|  |__| |_______|        \\______/\n";
    private static final String indent = "    ";
    private static final String doubleIndent = indent + indent;
    private static final String border = "_________________________________________________";

    // Prints Duke logo
    public static void printLogo() {
        System.out.println(logo);
    }

    // Prints a chat window showing the list of tasks
    public static void printTasksChatWindow(List<Task> tasks) {
        printBorder();
        printIndentedMessage("Here are the tasks in your list:");

        int index = 0;
        for (Task task : tasks) {
            System.out.printf("%s%d. %s\n", indent, ++index, task);
        }

        printBorder();
    }

    // Prints a chat window that describes a task that is done
    public static void printDoneTaskChatWindow(Task task) {
        printBorder();
        printIndentedMessage("Great! I've marked this task as done:");
        printDoubleIndentedMessage(task.toString());
        printBorder();
    }

    // Prints a chat window that describes a task that is deleted
    public static void printDeleteTaskChatWindow(Task task, int numOfTotalTasks) {
        printBorder();
        printIndentedMessage("Okay. I've removed this task:");
        printDoubleIndentedMessage(task.toString());
        printNumberOfTasks(numOfTotalTasks);
        printBorder();
    }

    // Prints a chat window with a customised "add task" description
    public static void printAddTaskChatWindow(Task task, int numOfTotalTasks) {
        printBorder();
        printIndentedMessage("Alright. I've added this task:");
        printDoubleIndentedMessage(task.toString());
        printNumberOfTasks(numOfTotalTasks);
        printBorder();
    }

    // Prints an indented chat window with a customised message
    public static void printGeneralChatWindow(String... messages) {
        printBorder();

        for (String message : messages) {
            printIndentedMessage(message);
        }

        printBorder();
    }

    // Prints a box with the symbol inside, i.e. [s]
    private static String box(String symbol) {
        return String.format("[%s]", symbol);
    }

    // Prints a border as in the chat window
    private static void printBorder() {
        System.out.printf("%s%s\n", indent, border);
    }

    // Prints an indented generic message
    private static void printIndentedMessage(String s) {
        System.out.printf("%s%s\n", indent, s);
    }

    // Prints a double-indented generic message
    private static void printDoubleIndentedMessage(String s) {
        System.out.printf("%s%s\n", doubleIndent, s);
    }

    // Prints the number of tasks left in the list
    private static void printNumberOfTasks(int n) {
        System.out.printf("%sNow you have %d tasks in the list.\n", indent, n);
    }
}
