import java.util.Scanner;

public class Ui {
    /**
     * Gets input from the user.
     *
     * @return the user input
     */
    public String getUserInput(Scanner sc) {
        return sc.next();
    }

    /**
     * Greets the user with a message when they start the application.
     */
    public void greetUser() {
        System.out.println("Hello! I am Duke\n" + "What can I do for you?");
    }

    /**
     * Prints the error message to the user.
     */
    public void showErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Shows exit message when user exits the application.
     */
    public void showExitMessage() {
        System.out.println("Goodbye! See you again soon!");
    }
}
