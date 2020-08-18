import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        /** Initialize objects required **/
        // Using Scanner for reading inputs
        Scanner scanner = new Scanner(System.in);
        // Initialize List for Duke to store stuff in
        ArrayList<String> list = new ArrayList<>();

        // Text Images
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String lineBreaker = "--.--.--.--.--.--.--.--.--.--.--." +
                "--.--.--.--.--.--.--.--.--.--.--";
        String outputBreaker = ">>> ";


        // Greetings
        System.out.println("Hello, I'm\n" + logo);
        System.out.println("What can I help you with today?");
        System.out.println("\n" + lineBreaker);
        System.out.println();

        // System Loop
        // Read user input
        String instructions = scanner.nextLine();
        // End only if user input is "bye"
        while (!instructions.equals("bye")) {
            switch(instructions) {
                default:
                    // Store messages by default
                    System.out.print(outputBreaker);
                    list.add(instructions);
                    System.out.println("added: " + instructions);
                    System.out.println("\n" + lineBreaker + "\n");
                    break;
            }

            // Read user input once more
            instructions = scanner.nextLine();
        }

        // Farewell message
        System.out.print(outputBreaker);
        System.out.println("Bye! Hoped I helped!");
        System.out.println("\n" + lineBreaker);
    }
}
