package duke;

import java.util.Scanner;

/**
 * Handles interaction with the user.
 */

public class Ui {
    private Scanner sc;

    /**
     * Constructor for duke.Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads next command from user.
     * @return Line of user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays error when user loads the program without an existing saved task list.
     */
    public String showLoadingError() {
        return ">> No data storage found. Creating new task list.";
    }

    /**
     * Greets the user on application boot.
     */
    public String greet() {
        return ">> Beep Boop. I am Aq-bot.\n>> How can I help?";
    }

    /**
     * Says bye to the user when application closes.
     */
    public String bye() {
        return ">> Bye! Hope I helped!";
    }

    /**
     * Displays error when user enters an invalid date for Dated Tasks.
     */
    public String invalidDateError() {
        return ">> Please format your date in YYYY-MM-DD format!";
    }

    /**
     * Displays error when user enters an incomplete instruction.
     */
    public String incompleteInstructionError() {
        return ">> This instruction is incomplete!";
    }

    /**
     * Displays error when user attempts to create a Dated duke.Task without a date.
     */
    public String conditionError(Constants.TaskTypes type) {
        return ">> Oh no!!! A " + type.toString().toLowerCase() + " must have an associated date!";
    }

    /**
     * Displays error when user attempts to delete a task that does not exist.
     */
    public String deleteError() {
        return ">> Oh no!!! That task does not exist!";
    }
}
