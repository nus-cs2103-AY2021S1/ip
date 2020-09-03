package duke;

/**
 * Deals with user interaction.
 */
public class Ui {

    /**
     * Constructor used to create UI .
     */
    public Ui() {
    }

    /**
     * Displays error message.
     *
     * @param e Duke Exception.
     */
    public String showError(DukeException e) {
        return printOutput(e.getMessage());
    }

    /**
     * Prints output in a nice format.
     *
     * @param input String input.
     */
    public String printOutput(String input) {
        return input;
    }

    public String showWelcome() {
        return printOutput("Hello! I'm Duke\n"
                + "What can I do for you?");
    }

    public String showExit() {
        return printOutput("Bye. Hope to see you again soon!");
    }
}
