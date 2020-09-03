package duke;

import java.util.Scanner;

/** Deals with interaction with the user. */
public class Ui {

    /** A decorative line to serve as a border. */
    public static final String LINE = "____________________________________________________________";

    /** The name of the Duke bot. */
    private final String botName = "Bolot";

    /** The logo for the Duke bot. */
    private final String logo = "Greetings, human. I am\n"
        + " ______      ___   _____       ___    _________\n"
        + "|_   _ \\   .'   `.|_   _|    .'   `. |  _   _  |\n"
        + "  | |_) | /  .-.  \\ | |     /  .-.  \\|_/ | | \\_|\n"
        + " _| |__) |\\  `-'  /_| |__/ |\\  `-'  /   _| |_\n"
        + "|_______/  `.___.'|________| `.___.'   |_____|";

    /** The scanner used to read user inputs. */
    private final Scanner sc = new Scanner(System.in);

    /** Prints the logo of the Duke bot. */
    private void printLogo() {
        System.out.println(logo);
    }

    /** Greets the user. */
    private void greet() {
        System.out.println(LINE);
        System.out.println("Hello! I am " + botName + ", your personal chat-bot companion.");
        System.out.println("How may I help you?");
        System.out.println(LINE);
    }

    /** Prints the logo and greets the user. */
    public void showWelcome() {
        printLogo();
        greet();
    }

    /**
     * Says goodbye to the user.
     *
     * @return The goodbye string.
     */
    public String bye() {
        sc.close();
        return String.format("Bye! Thank you for chatting with %s!\n"
            + "Hope to see you again soon!", botName);
    }

    /** Prints the border line. */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Reads the input from the user.
     *
     * @return The user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /** Prints the error message. */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /** Prints the error message. */
    public void showLoadingError() {
        System.out.println("Unable to load the data. Creating new file...");
    }

    /**
     * Prints the message given.
     *
     * @param msg The message to be printed.
     */
    public void printMessage(String msg) {
        System.out.println(msg);
    }
}
