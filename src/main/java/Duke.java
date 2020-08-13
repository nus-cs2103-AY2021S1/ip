import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _         -----------\n"
                + "|  _ \\ _   _| | _____  \\__    __/\n"
                + "| | | | | | | |/ / _ \\    |  |\n"
                + "| |_| | |_| |   <  __/  __|  |\n"
                + "|____/ \\__,_|_|\\_\\___|  \\____/\n";
        System.out.println(logo);
        printChatWindow("Greetings! I'm Duke J.", "What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        // Loop continues echoing input until input == "bye"
        while (!input.equals("bye")) {
            printChatWindow(input);
            input = sc.next();
        }

        printChatWindow("Have a nice day. Goodbye!");
    }

    public static void printChatWindow(String... messages) {
        String indent = "    ";
        String border = "________________________________";
        System.out.printf("%s%s\n", indent, border);

        for (String message : messages) {
            System.out.printf("%s%s\n", indent, message);
        }

        System.out.printf("%s%s\n", indent, border);

    }
}
