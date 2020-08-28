package duke;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    public void printResponse(String message) {
        String divider = "\n\t-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n";
        System.out.println(divider.concat("\t".concat(message.concat("\n"))).concat(divider));
    }

    public void showWelcome() {
        String greetMessage = "Hello! I'm AiBot :)\n\tEnter the command you would like to do\n\tEnter 'bye' to exit";
        printResponse(greetMessage);
    }

    public void showBye() {
        String exitMessage = "Bye. See you again soon!";
        printResponse(exitMessage);
        scanner.close();
    }

    public void showError(String errorMessage) {
        printResponse("Error: " + errorMessage);
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
