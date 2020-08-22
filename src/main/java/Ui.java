import java.util.Scanner;

/**
 * Handles interaction with the user.
 */

public class Ui {
    private Scanner sc;

    /**
     * Constructor for Ui.
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
    public void showLoadingError() {
        System.out.println(">> No data storage found. Creating new task list.");
    }

    /**
     * Greets the user on application boot.
     */
    public void greet() {
        System.out.println(">> Beep Boop. I am Aq-bot.\n>> How can I help?");
    }

    /**
     * Says bye to the user when application closes.
     */
    public void bye() {
        System.out.println(">> Bye! Hope I helped!");
    }

    /**
     * Displays error when user enters an invalid date for Dated Tasks.
     */
    public void invalidDateError() {
        System.out.println(">> Please format your date in YYYY-MM-DD format!");
    }

    /**
     * Displays error when user enters an incomplete instruction.
     */
    public void incompleteInstructionError() {
        System.out.println(">> This instruction is incomplete!");
    }

    /**
     * Displays error when user attempts to create a Dated Task without a date.
     */
    public void conditionError(Constants.TaskTypes type) {
        System.out.println(">> Oh no!!! A " + type.toString().toLowerCase() + " must have an associated date!");
    }

    /**
     * Displays error when user attempts to delete a task that does not exist.
     */
    public void deleteError() {
        System.out.println(">> Oh no!!! That task does not exist!");
    }
}
