import java.util.Scanner;

import javafx.scene.control.Label;

/**
 * Encapsulates the user interface which user uses to interact with Duke.
 */
public class UI {

    /**
     * Duke's logo.
     */
    private static final String LOGO = "____        _        \n"
                                     + "|  _ \\ _   _| | _____ \n"
                                     + "| | | | | | | |/ / _ \\\n"
                                     + "| |_| | |_| |   <  __/\n"
                                     + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String DIVIDER = "______________________________________________";

    /**
     * Input system using Java's scanner.
     */
    private static final Scanner in = new Scanner(System.in);

    private Label outputDisplay;

    public void setOutput(Label outputDisplay) {
        this.outputDisplay = outputDisplay;
    }

    /**
     * Gives the user a prompt to input commands.
     *
     * @return The user's input.
     */
    public String prompt() {
        System.out.print(">> ");
        String response = in.nextLine();
        return response;
    }

    /**
     * Prints the given message between dividers.
     *
     * @param message Message to be printed.
     */
    public void print(String message) {
        outputDisplay.setText(message);
    }

    /**
     * Greets the user.
     */
    public String greet() {
        String greeting = "Hi! I am " + "DUKE." + "\n" + "What can I do for you?";
        return greeting;
    }

    /**
     * Exits the program with farewell message.
     */
    public void exit() {
        String goodbye = "Bye! Hope to see you again!";
        print(goodbye);
        System.exit(0);
    }
}
