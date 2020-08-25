import java.util.Scanner;

/**
 * Ui prints outputs to interact with users.
 */
public class Ui {
    private Scanner sc;

    /**
     * Ui constructor to initialise scanner.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Read the input from user and return it as a string.
     *
     * @return String
     */
    public String readCommand() {
        String temp = null;
        if (sc.hasNextLine()) {
            temp = sc.nextLine();
        }
        return temp;
    }

    /**
     * Print the error.
     *
     * @param error String
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Show a horizontal dashes.
     */
    public void showLine() {
        String line = "--------------------------------------";
        System.out.println(line);
    }

    /**
     * Show loading error when loading storage.
     */
    public void showLoadingError() {
        String error = "error loading Duke";
        System.out.println(error);
    }

    /**
     * Print welcome when the Duke is started.
     */
    public void showWelcome() {
        String greeting = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greeting);
    }

    /**
     * Stop Duke and close scanner.
     */
    public void closeDuke() {
        String bye = "Bye. Hope to see you again soon!";
        sc.close();
        System.out.println(bye);
    }
}