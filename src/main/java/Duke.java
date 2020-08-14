import java.util.ArrayList;
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
        ArrayList<String> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Listens for input
            System.out.print("Enter text or bye to quit: ");
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                // Encounters exit command
                System.out.println();
                indent();
                System.out.println("Bye bye! Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                // Prints the given list
                printList(list);
            } else if (!input.isBlank()) {
                // Stores text given
                list.add(input);
                indent();
                System.out.print("Successfully added: " + input + "\n\n");
            } else {
                // Returns blank line
                System.out.println();
            }
        }
    }

    public static void printList(ArrayList<String> list) {
        if (list.isEmpty()) {
            return;
        }
        int listPos = 1;
        for (int i = 0; i < list.size(); i++,listPos++) {
            indent();
            System.out.println(listPos + ". " + list.get(i));
        }
        System.out.println();
    }

    public static void indent() {
        System.out.print("    ");
    }
}
