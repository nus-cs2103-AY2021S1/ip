import java.util.List;

public class Printer {
    private static final String indent = "    ";
    private static final String doubleIndent = indent + indent;
    private static final String border = "_________________________________________________";

    private static final String tickedBox = box("\u2713");
    private static final String crossedBox = box("\u2718");

    // Prints a chat window showing the list of tasks
    public static void printTasksChatWindow(List<Task> tasks) {
        printBorder();
        int index = 0;

        for (Task task : tasks) {
            System.out.printf("%s%d. %s\n", indent, ++index, task);
        }

        printBorder();
    }

    // Prints a chat window that describes a task that is done
    public static void printDoneTaskChatWindow(Task task) {
        printBorder();

        System.out.printf("%sGreat! I've marked this task as done:\n", indent);
        System.out.printf("%s%s\n", doubleIndent, task);

        printBorder();
    }

    // Prints a chat window with a customised "add task" description
    public static void printAddTaskChatWindow(Task task, int numOfTotalTasks) {
        printBorder();

        System.out.printf("%sAlright. I've added this task:\n", indent);
        System.out.printf("%s%s\n", doubleIndent, task);
        System.out.printf("%sNow you have %d tasks in the list.\n", indent, numOfTotalTasks);

        printBorder();
    }

    // Prints an indented chat window with a customised message
    public static void printGeneralChatWindow(String... messages) {
        printBorder();

        for (String message : messages) {
            System.out.printf("%s%s\n", indent, message);
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
}
