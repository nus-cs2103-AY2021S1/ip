import java.util.Scanner;

/**
 * Class to handle input and output of program.
 */
public class Ui {

    // Attributes
    private final Scanner sc;

    // Constants
    private static final String NAME = "DUKE";
    private static final String INTRODUCTION = String.format(
            "Hello! I'm %s\n" +
                    "I was created for the module CS2103T\n" +
                    "What can I do for you?", NAME);

    // Constructor

    /**
     * Creates a new Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    // Methods

    /**
     * Reads the next line from standard input.
     * @return The next line from standard input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Returns whether there is another line in standard input.
     * @return Boolean representing whether there is another line in standard input.
     */
    public boolean hasCommand() {
        return sc.hasNext();
    }

    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        showLine();
        showMessage(INTRODUCTION);
        showLine();
    }

    /**
     * Prints a line.
     */
    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints a given message.
     * @param message message to be printed.
     */
    public void showMessage(String message) {
        System.out.println("    " + message.replaceAll("\n", "\n    "));
    }
}
