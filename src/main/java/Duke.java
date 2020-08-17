import java.util.Scanner;

public class Duke {
    private static final String DIVIDER = "----------------------------";

    private static String sandwichWithDivider(String text) {
        return DIVIDER + "\n" + text + "\n" + DIVIDER;
    }

    private static String processCommand(String command) {
        return sandwichWithDivider(command);
    }

    private static final String HELLO_MESSAGE = sandwichWithDivider("Greetings! I am Duke."
            + "\nWhat can I do for you?");
    private static final String EXIT_MESSAGE = sandwichWithDivider("Bye! See you around!");

    public static void main(String[] args) {
        String exitCommand = "bye";
        Scanner sc = new Scanner(System.in);

        System.out.println(HELLO_MESSAGE);
        while (true) {
            String command = sc.next();
            if (command.equals(exitCommand)) {
                System.out.println(EXIT_MESSAGE);
                break;
            } else {
                System.out.println(processCommand(command));
            }
        }

        sc.close();
    }
}
