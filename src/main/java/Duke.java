import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _         -----------\n"
                + "|  _ \\ _   _| | _____  \\__    __/\n"
                + "| | | | | | | |/ / _ \\    |  |\n"
                + "| |_| | |_| |   <  __/  __|  |\n"
                + "|____/ \\__,_|_|\\_\\___|  \\____/\n";
        System.out.println(logo);
        printGeneralChatWindow("Greetings! I'm Duke J.", "What can I do for you?");

        // Assuming no more than 100 tasks
        final int taskCapacity = 100;

        // Initialise a fixed array of tasks to store
        List<String> tasks = new ArrayList<>(taskCapacity);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        // Loop continues echoing input until input == "bye"
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printTasksChatWindow(tasks);
            } else {
                tasks.add(input);
                printGeneralChatWindow("added: " + input);
            }
            input = sc.nextLine();
        }

        printGeneralChatWindow("Have a nice day. Goodbye!");
    }

    public static void printTasksChatWindow(List<String> tasks) {
        String indent = "    ";
        String border = "________________________________";
        int index = 0;
        System.out.printf("%s%s\n", indent, border);

        for (String task : tasks) {
            System.out.printf("%s%d. %s\n", indent, ++index, task);
        }

        System.out.printf("%s%s\n", indent, border);
    }

    public static void printGeneralChatWindow(String... messages) {
        String indent = "    ";
        String border = "________________________________";
        System.out.printf("%s%s\n", indent, border);

        for (String message : messages) {
            System.out.printf("%s%s\n", indent, message);
        }

        System.out.printf("%s%s\n", indent, border);
    }
}
