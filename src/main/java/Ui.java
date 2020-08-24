import java.util.Scanner;

public class Ui {

    Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Show the welcome message.
     */
    public void showWelcome() {
        System.out.println("    ___________________________________________________________");
        System.out.println("    Hello! I'm Duke\n    What can I do for you?");
        System.out.println("    ___________________________________________________________");
    }

    /**
     * Read the next user input.
     *
     * @return The next user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Show the divider line.
     */
    public void showLine() {
        System.out.println("    ___________________________________________________________");
    }

    /**
     * Show the loading error message.
     */
    public void showLoadingError() {
        System.out.println("Error loading file");
    }

    /**
     * Show the error message.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
