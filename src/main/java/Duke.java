import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<String> items = new ArrayList<>();

    public static void printGreeting() {
        String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        printPrompt(LOGO
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
        );
    }

    private static String createPrompt(String promptText) {
        String HORIZONTAL_LINE = "____________________________________________________________";
        String INDENTATION = "    ";
        String[] lines = promptText.split("[\\r\\n]+");
        StringBuilder output = new StringBuilder(INDENTATION + HORIZONTAL_LINE + '\n');

        for (String line : lines) {
            output.append(INDENTATION).append(line).append('\n');
        }

        output.append(INDENTATION).append(HORIZONTAL_LINE).append('\n');

        return output.toString();
    }

    public static void printPrompt(String promptText) {
        System.out.println(createPrompt(promptText));
    }

    public static void addItem(String item) {
        items.add(item);
        printPrompt("added: " + item);
    }

    public static void listItems() {
        StringBuilder output = new StringBuilder();

        for (String item : items) {
            output.append(items.indexOf(item) + 1).append(". ").append(item).append('\n');
        }

        printPrompt(output.toString());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printGreeting();

        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                listItems();
            } else {
                addItem(command);
            }

            command = scanner.nextLine();
        }

        printPrompt("Bye. Hope to see you again soon!");
    }
}
