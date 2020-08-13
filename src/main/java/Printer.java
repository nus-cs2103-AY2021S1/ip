import java.util.List;

public class Printer {
    private static final String indent = "    ";
    private static final String doubleIndent = indent + indent;
    private static final String border = "________________________________________";

    private static final String tickedBox = box("\u2713");
    private static final String crossedBox = box("\u2718");

    public static void printTasksChatWindow(List<Task> tasks) {
        printBorder();
        int index = 0;

        for (Task task : tasks) {
            String taskDescription = task.getDescription();
            String checkBox = box(task.getStatusIcon());
            System.out.printf("%s%d.%s %s\n", indent, ++index, checkBox, taskDescription);
        }

        printBorder();
    }

    public static void printDoneTask(Task task) {
        printBorder();

        System.out.printf("%sGreat! I've marked this task as done:\n", indent);
        System.out.printf("%s%s %s\n", doubleIndent, tickedBox, task.getDescription());

        printBorder();
    }

    public static void printGeneralChatWindow(String... messages) {
        printBorder();

        for (String message : messages) {
            System.out.printf("%s%s\n", indent, message);
        }

        printBorder();
    }

    private static String box(String symbol) {
        return String.format("[%s]", symbol);
    }

    private static void printBorder() {
        System.out.printf("%s%s\n", indent, border);
    }
}
