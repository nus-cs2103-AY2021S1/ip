import java.util.List;

public class Ui {
    private static final String logo = "     ___   ____    __    ____  _______     _______.  ______   .___  ___.  _______          ______\n"
            + "    /   \\  \\   \\  /  \\  /   / |   ____|   /       | /  __  \\  |   \\/   | |   ____|        /  __  \\\n"
            + "   /  ^  \\  \\   \\/    \\/   /  |  |__     |   (----`|  |  |  | |  \\  /  | |  |__    ______|  |  |  |\n"
            + "  /  /_\\  \\  \\            /   |   __|     \\   \\    |  |  |  | |  |\\/|  | |   __|  |______|  |  |  |\n"
            + " /  _____  \\  \\    /\\    /    |  |____.----)   |   |  `--'  | |  |  |  | |  |____        |  `--'  |\n"
            + "/__/     \\__\\  \\__/  \\__/     |_______|_______/     \\______/  |__|  |__| |_______|        \\______/\n";
    private static final String indent = "    ";
    private static final String doubleIndent = indent + indent;
    private static final String border = "_________________________________________________";

    // Initial greeting, prompt user for commands
    public void printWelcome() {
        printLogo();
        printGeneralChatWindow("Greetings! I'm Awesome-O.", "What can I do for you?");
    }

    // Prints a chat window showing the list of tasks
    public void printTasksChatWindow(List<Task> tasks) {
        printBorder();
        printIndentedMessage("Here are the tasks in your list:");

        if (tasks.isEmpty()) {
            printIndentedMessage("No tasks currently");
        } else {
            int index = 0;
            for (Task task : tasks) {
                System.out.printf("%s%d. %s\n", indent, ++index, task);
            }
        }

        printBorder();
    }

    // Prints a chat window that describes a task that is done
    public void printDoneTaskChatWindow(Task task) {
        printBorder();
        printIndentedMessage("Great! I've marked this task as done:");
        printDoubleIndentedMessage(task.toString());
        printBorder();
    }

    // Prints a chat window that describes a task that is deleted
    public void printDeleteTaskChatWindow(Task task, int numOfTotalTasks) {
        printBorder();
        printIndentedMessage("Okay. I've removed this task:");
        printDoubleIndentedMessage(task.toString());
        printNumberOfTasks(numOfTotalTasks);
        printBorder();
    }

    // Prints a chat window with a customised "add task" description
    public void printAddTaskChatWindow(Task task, int numOfTotalTasks) {
        printBorder();
        printIndentedMessage("Alright. I've added this task:");
        printDoubleIndentedMessage(task.toString());
        printNumberOfTasks(numOfTotalTasks);
        printBorder();
    }

    // Print goodbye chat window
    public void printGoodbye() {
        printGeneralChatWindow("Thank you for talking to Awesome-O.", "Have a nice day. Goodbye!");
        printLogo();
    }

    // Prints an indented chat window with a customised message
    public void printGeneralChatWindow(String... messages) {
        printBorder();

        for (String message : messages) {
            printIndentedMessage(message);
        }

        printBorder();
    }

    // Prints Duke logo
    private void printLogo() {
        System.out.println(logo);
    }

    // Prints a box with the symbol inside, i.e. [s]
    private String box(String symbol) {
        return String.format("[%s]", symbol);
    }

    // Prints a border as in the chat window
    private void printBorder() {
        System.out.printf("%s%s\n", indent, border);
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
