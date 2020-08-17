import java.util.Scanner;

public class Duke {
    public static void printGreeting() {
        String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Duke.printPrompt(LOGO
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
        System.out.println(Duke.createPrompt(promptText));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Duke.printGreeting();

        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            Duke.printPrompt(command);
            command = scanner.nextLine();
        }

        Duke.printPrompt("Bye. Hope to see you again soon!");
    }
}
