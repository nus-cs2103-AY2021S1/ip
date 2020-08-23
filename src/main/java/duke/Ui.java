package duke;

/**
 * Encapsulates the User Interface.
 * Handles the user interactions which are not relevant to the main program.
 */

public class Ui {

    /**
     * Greets the user when Duke is opened.
     */
    public void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm duke. What can I do for you? \n" + logo);
    }

    /**
     * Used to wrap around the output.
     */
    public void line() {
        for (int i = 0; i < 75; i++) {
            System.out.print("\u2500");
        }
        System.out.println();
    }

    /**
     * Greets the user upon exiting Duke.
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

}
