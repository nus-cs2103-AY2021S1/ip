import java.util.Scanner;

/**
 * Handles what the user will be seeing when running Duke.
 */
public class Ui {

    Scanner sc;
    public Ui() {
        sc = new Scanner(System.in);
    }
    public void showLine() {
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Show user the greeting message.
     */
    public void showGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Show error message thrown.
     *
     * @param errorMsg Error message of thrown error.
     */
    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }

    /**
     * Reads in the next input line from user.
     *
     * @return The next input from user.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Print the message according to the input given.
     *
     * @param input Output for the user to read.
     */
    public void printOutput(String input) {
        System.out.println(input);
    }
}
