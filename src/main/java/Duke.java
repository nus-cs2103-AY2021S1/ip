import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String introduction =
                "Hi, I'm your Professor, Martin." +
                "\nWhat can I do for you?";
        System.out.println(introduction);
        Scanner sc = new Scanner(System.in);
        String input = "";

        // Loop program until command 'bye' is entered as input.
        while (true) {
            try {
                System.out.print("Enter command: ");
                input = sc.nextLine();
                processCommand(input);
                if (!input.equals("bye")) {
                    System.out.println("Restarting...");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Exception: " + e.toString());
            }
        }
        sc.close();
    }

    public static void processCommand(String command) {
        if (command.equals("bye") ) {
            System.out.println("Bye. Hope to see you again soon!");
        } else {
            System.out.println("Echoing command: " + command);
        }
    }
}
