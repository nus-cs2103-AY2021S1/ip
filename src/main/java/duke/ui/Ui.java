package duke.ui;

import java.util.Scanner;

/**
 * Represents a UI to handle user interaction.
 * @author Tee Kok Siang
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);
    public static final String GREET_MESSAGE = "Hello! I'm AiBot :)\n\tEnter the command you would like to do\n\tEnter 'bye' to exit";
    public static final String EXIT_MESSAGE = "Bye. See you again soon!";

    public void printResponse(String message) {
        String divider = "\n\t-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n";
        System.out.println(divider.concat("\t".concat(message.concat("\n"))).concat(divider));
    }

    public void showWelcome() {
        printResponse(GREET_MESSAGE);
    }

    public void showBye() {
        printResponse(EXIT_MESSAGE);
        scanner.close();
    }

    public void showError(String errorMessage) {
        printResponse("Error: " + errorMessage);
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
