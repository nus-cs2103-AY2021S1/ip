package logic;

import java.util.Scanner;

/**
 * Handles what the user will be seeing when running logic.Duke.
 */
public class Ui {

    private Scanner sc;
    public Ui() {
        sc = new Scanner(System.in);
    }
    public void showLine() {
        System.out.println("\t______________________________________________________");
    }

    /**
     * Show user the greeting message.
     */
    public String showGreeting() {
        String result = "";
        String logo = "\n ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        result += "Hello from" + logo;
        result += "\t__________________________________________________\n";
        result += "\tHello! I'm Duke\n\tWhat can I do for you?\n";
        result += "\t__________________________________________________\n";
        return result;
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
    public String printOutput(String input) {
        return input;
    }
}
