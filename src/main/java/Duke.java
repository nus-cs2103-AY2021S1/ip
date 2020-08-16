import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        // Print introduction
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");

        // Receive commands
        Scanner scanner = new Scanner(System.in);
        String command = scanner.next();
        while (!command.equals("bye")) {
            System.out.println(command + "\n");
            command = scanner.next();
        }

        // Exit Duke
        System.out.println("Bye. Hope to see you again soon!");

    }
}
