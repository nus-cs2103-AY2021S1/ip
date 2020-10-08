package duke.inputoutput;

import static duke.utils.Messages.MESSAGE_BYE;
import static duke.utils.Messages.MESSAGE_GREETING;
import static duke.utils.Messages.MESSAGE_WELCOME;

import java.util.Scanner;

/**
 * Represents the Ui of the system that handles user interaction.
 */
public class InputOutput {

    private static final String LINE = "\t" + "_".repeat(75);
    private Scanner sc;

    /**
     * Constructs a Ui object.
     */
    public InputOutput() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Displays a line to the user.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    private void showWelcome() {
        System.out.println(MESSAGE_WELCOME);
    }

    /**
     * Displays the greeting to the user. Called when the user first runs the program.
     */
    public void showGreeting() {
        showWelcome();
        showLine();
        System.out.println(MESSAGE_GREETING);
        showLine();
    }

    /**
     * Displays the goodbye message to the user.
     */
    public void showBye() {
        showLine();
        System.out.println(MESSAGE_BYE);
        showLine();
    }

    /**
     * Displays the specified message to the user.
     *
     * @param message The message to be displayed.
     */
    public void show(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }

    /**
     * Reads the user input.
     *
     * @return The user input as a String.
     */
    public String readCommand() {
        if (sc.hasNextLine()) {
            return sc.nextLine();
        } else {
            return "bye";
        }
    }

}
