import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Using Scanner for reading inputs
        Scanner scanner = new Scanner(System.in);

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
                    // Echo messages by default
                    System.out.print(outputBreaker);
                    System.out.println(instructions);
                    System.out.println("\n" + lineBreaker);
                    System.out.println();
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
