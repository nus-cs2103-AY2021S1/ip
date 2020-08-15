import java.util.Scanner;

public class Duke {
    private static final String BYE = "bye";
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke.displayMessage("What can I do for you?");

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("> ");
            while (scanner.hasNextLine()) {
                String command = scanner.nextLine();

                if (command.equalsIgnoreCase(BYE)) {
                    break;
                } else {
                    Duke.displayMessage(command);
                    System.out.print("> ");
                }
            }

            Duke.displayMessage(GOODBYE_MESSAGE);
        }
    }

    private static void displayMessage(String message) {
        System.out.println("     ――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
        System.out.println("     " + message);
        System.out.println("     ――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
        System.out.println();
    }
}
