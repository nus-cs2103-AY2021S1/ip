import java.util.Scanner;

public class Duke {

    private static void writeOutput(String... messages) {
        System.out.println("\t-----------------------------------------");
        for (String message : messages) {
            System.out.println("\t" + message);
        }
        System.out.println("\t-----------------------------------------");
    }

    public static void main(String[] args) {
        writeOutput("Hello! I'm Duke", "What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        String input;
        boolean keepGoing = true;
        while (keepGoing) {
            input = scanner.nextLine();
            if (!input.equals("bye")) {
                writeOutput(input);
            } else {
                writeOutput("Bye. Hope to see you again soon!");
                keepGoing = false;
            }
        }
        scanner.close();

    }
}
