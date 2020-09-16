package duke.ui;

import java.util.Scanner;

/**
 * Deals the interactions with the user.
 */
@SuppressWarnings("checkstyle:Regexp")
public class Ui {
    private static final String LINE = "_______________________________________\n";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads in input from user.
     * @return the input from user.
     */
    public String getUserInput() {
        return this.scanner.nextLine();
    }

    /**
     * Greets the user. Runs when program is opened.
     */
    public void welcome() {
        String open = "_______________________________________ \n"
                + "Hello! I'm duke.Duke \n"
                + "What can I do for you? \n"
                + "_______________________________________ \n";
        System.out.println(open);
    }

    /**
     * Prints Duke's response with dividing lines
     * @param response Duke's response
     */
    public void printResponse(String response) {
        System.out.println(LINE + response + "\n" + LINE);
    }
}
