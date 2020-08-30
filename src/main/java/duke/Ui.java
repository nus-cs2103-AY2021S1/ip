package duke;

import java.util.Scanner;

/** Deals with interaction with the user. */
public class Ui {

    /** A decorative line to serve as a border. */
    public static final String LINE = "____________________________________________________________";

    /** The name of the Duke bot. */
    private final String botName = "Bolot";

    /** The scanner used to read user inputs. */
    private final Scanner sc = new Scanner(System.in);

    /** Prints the logo of the Duke bot. */
    private void printLogo() {
        System.out.println("Greetings, human. I am");
        System.out.println(" ______      ___   _____       ___    _________");
        System.out.println("|_   _ \\   .'   `.|_   _|    .'   `. |  _   _  |");
        System.out.println("  | |_) | /  .-.  \\ | |     /  .-.  \\|_/ | | \\_|");
        System.out.println(" _| |__) |\\  `-'  /_| |__/ |\\  `-'  /   _| |_");
        System.out.println("|_______/  `.___.'|________| `.___.'   |_____|");
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

    /** Says goodbye to the user. */
    public String bye() {
        sc.close();
        return String.format("Bye! Thank you for chatting with %s!\n"
            + "Hope to see you again soon!", botName);
    }

    /** Prints the border line. */
    public void showLine() {
        System.out.println(LINE);
    }

    /** Reads the input from the user. */
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
}
