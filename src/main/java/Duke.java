import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printLogo();
        startGreet();
        startChat();
    }
    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    public static void startGreet() {
        String greeting = "Hello! I'm Duke the chatbot! \n" +
                "What can I do for you? \n";
        System.out.println(greeting);
    }

    public static void startChat() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            // Listens for input
            System.out.print("Enter a command: ");
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                // Encounters exit command
                System.out.println("Bye bye! Hope to see you again soon!");
                break;
            } else if (!input.isBlank()) {
                // Parrots the given command
                System.out.print("You have entered: " + input + "\n\n");
            } else {
                // Returns blank line
                System.out.println();
            }
        }
    }
}
