package duke;

import java.util.Scanner;

/**
 * Represents the user interface that deals with interactions with the user.
 */
public class Ui {
    /** Scanner to take in user input */
    private final Scanner sc = new Scanner(System.in);

    /**
     * Public constructor. Should only be used by bot.
     */
    public Ui() { }

    /**
     * Displays the welcome message to the user.
     * @return
     */
    public String returnWelcomeMsg() {
        return "Eh what's up! I'm Meimei" + "\nWhat you want ah?";
    }

    /**
     * Reads user inputs which should contain commands to the bot.
     *
     * @return the line inputted by the user
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints lines to frame bot responses.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Returns a string with the response from the bot as given by executing a command.
     *
     * @return response from Duke
     */
    public String returnReply(String reply) {
        return reply;
    }

    public String returnError(String message) {
        return message;
    }
}
