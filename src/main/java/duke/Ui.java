package duke;

import java.util.Scanner;

/**
 * Deals with user interaction.
 */
public class Ui {

    /** Takes in user input. */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructor used to create UI .
     */
    public Ui() {
    }

    /**
     * Reads the next line.
     *
     * @return Next line.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays error message.
     *
     * @param e Duke Exception.
     */
    public String showError(DukeException e) {
        return printOutput(e.getMessage(), true);
    }

    /**
     * Prints output in a nice format.
     *
     * @param input String input.
     * @param hasLastNewLine Option to choose if an extra line break is needed.
     */
    public String printOutput(String input, boolean hasLastNewLine) {
        if (hasLastNewLine) {
            return "    ____________________________________________________________\n"
                    + input + "\n"
                    + "    ____________________________________________________________";

        } else {
            return "    ____________________________________________________________\n"
                    + input + "    ____________________________________________________________";
        }
    }

    public String showWelcome() {
        return printOutput("     Hello! I'm Duke\n"
                + "     What can I do for you?", true);
    }

    public String showExit() {
        return printOutput("     Bye. Hope to see you again soon!", true);
    }
}
