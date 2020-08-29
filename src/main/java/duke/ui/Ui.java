package duke;

import java.util.Scanner;

/**
 * Utility class that handles interactions with the user.
 */
public class Ui {
    
    Scanner sc;

    /**
     * Initialises a new Ui object
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Show a greeting message to the user.
     */
    public void sayHi() {
        String logo = ",--.                           ,--. \n"
                + "|  |-.  ,---. ,--.--. ,---.  ,-|  | \n"
                + "| .-. '| .-. ||  .--'| .-. :' .-. | \n"
                + "| `-' |' '-' '|  |   \\   --.\\ `-' | \n"
                + " `---'  `---' `--'    `----' `---'  \n\n";
        StringBuilder message = new StringBuilder("Hi I'm\n").append(logo).append("Please give me something to do.");
        botOutput(message);
    }

    /**
     * Shows a farewell message to the user.
     */
    public void sayBye() {
        botOutput("Come back soon!! I'm always bored...");
    }

    /**
     * Shows an error message from a DukeException to the user.
     * @param e Exception containing the error message.
     */
    public void showError(DukeException e) {
        botOutput(e.getMessage());
    }

    /**
     * Outputs a message to the user.
     * @param message Message to be shown to the user.
     */
    public void botOutput(String message) {
        String divider = "____________________________________________________________";
        System.out.println(divider);
        System.out.println(message);
        System.out.println(divider);
    }

    public void botOutput(StringBuilder message) {
        botOutput(message.toString());
    }

    /**
     * Reads a command from the user.
     * @return Command read from the user.
     */
    public String readNextCommand() {
       return sc.nextLine();
    }
}
