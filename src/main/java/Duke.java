import java.util.Scanner;

public class Duke {
    private static final String GREET_MESSAGE = "Hello! I'm AiBot :)\n\tEnter the command you would like to do\n\tEnter 'bye' to exit";
    private static final String EXIT_MESSAGE = "Bye. See you again soon!";
    private static final String DIVIDER = "\n\t-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n";

    private static void printResponse(String message) {
        System.out.println(DIVIDER.concat("\t".concat(message.concat("\n"))).concat(DIVIDER));
    }

    private static void listenCommand() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equalsIgnoreCase("bye")) {
            printResponse(command);
            command = scanner.nextLine();
        }

        scanner.close();
    }

    public static void main(String[] args) {
        printResponse(GREET_MESSAGE);
        listenCommand();
        printResponse(EXIT_MESSAGE);
    }
}
