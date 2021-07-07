package duke;

/**
 * Encapsulates the User Interface.
 * Handles the user interactions which are not relevant to the main program.
 */

public class Ui {

    /**
     * Greets the user when Duke is opened.
     * @return A string to greet the user.
     */
    public String welcome() {
        return "Hello! I'm duke. What can I do for you?";
    }

    /**
     * Used to wrap around the output.
     */
    public void displayLine() {
        for (int i = 0; i < 75; i++) {
            System.out.print("\u2500");
        }
        System.out.println();
    }

    /**
     * Greets the user upon exiting Duke.
     * @return A string to greet the user.
     */
    public String exit() {
        return "Bye. Hope to see you again soon!";
    }
}
