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
    public static String greetUser() {
        return "Hello! I am Duke\n" + "What can I do for you?";
    }

    /**
     * Prints the error message to the user.
     */
    public void showErrorMessage(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Returns exit message when user exits the application.
     *
     * @return the exit message.
     */
    public String getExitMessage() {
        return "Goodbye! See you again soon!";
    }
}
