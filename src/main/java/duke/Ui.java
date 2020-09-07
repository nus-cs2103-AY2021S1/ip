package duke;

/**
 * Deals with user interaction.
 */
class Ui {

    /**
     * Constructor used to create UI .
     */
    Ui() {
    }

    /**
     * Displays error message.
     *
     * @param e Duke Exception.
     */
    String showError(DukeException e) {
        return printOutput(e.getMessage());
    }

    /**
     * Prints output in a nice format.
     *
     * @param input String input.
     */
    String printOutput(String input) {
        return input;
    }

    String showWelcome() {
        return printOutput("Hello! I'm Duke\n"
                + "What can I do for you?");
    }

    String showExit() {
        return printOutput("Bye. Hope to see you again soon!");
    }
}
